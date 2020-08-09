package com.boss.bes.paper.service;


import boss.bes.log.exception.code.enums.PaperManageCodeEnum;
import boss.bes.log.exception.type.BusinessException;
import boss.brs.xtrain.dataconvention.service.BaseCURDService;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.boss.bes.paper.dao.PaperDao;
import com.boss.bes.paper.pojo.dto.PaperDTO;
import com.boss.bes.paper.pojo.dto.PaperSubjectDTO;
import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.utils.BeanUtil;
import com.boss.bes.paper.utils.mapper.PaperMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;


import java.util.Date;
import java.util.List;

/**
 * 试卷service类
 * @author yhy
 * time 2020.07.10 11:05
 */
@Service
public class PaperService extends BaseCURDService<PaperDTO, Paper, PaperDTO, PaperMapper> {
    @Autowired
    private PaperDao<Paper> paperDao;
    @Autowired
    PaperSubjectService paperSubjectService;
    private Long paperId;
    public Long getPaperId(){
        return this.paperId;
    }
    /**
     * 分页查询展示试卷信息
     * @param paperDTO
     * @param templateTag
     * @return
     */
    public PageInfo<PaperDTO> queryList(PaperDTO paperDTO,Boolean templateTag) {
        Example example = new Example(Paper.class);
        example.orderBy("updatedTime").desc();
        example.createCriteria()
                .andLike("paperName", "%" + paperDTO.getPaperName() + "%")
                .andLike("combExamMan", "%" + paperDTO.getCombExamMan() + "%")
                .andEqualTo("templateTag", templateTag)
                .andBetween("combExamTime", paperDTO.getStartTime(), paperDTO.getEndTime())
                .andEqualTo("difficulty", paperDTO.getDifficulty())
                .andEqualTo("paperType", paperDTO.getPaperType());

        List<Paper> papers = paperDao.queryWithPage(example);
        PageInfo<Paper> pageInfo = new PageInfo<>(papers, 5);
        return BeanUtil.convertPageInfo(pageInfo, PaperDTO.class);
    }

    /**
     * 全部试卷信息
     * @return
     */
    public List<PaperDTO> getAll(){
        Example example = new Example(Paper.class);
        example.orderBy("updatedTime").desc();
        example.createCriteria().andEqualTo("templateTag", false);
        List<Paper> papers = paperDao.queryWithPage(example);
        return BeanUtil.convertListToList(papers,PaperDTO.class);
    }
    /**
     * 转换类型为PaperDTO
     * @param o
     * @return
     */
    protected PaperDTO doPaperTransf(Object o) {
        return BeanUtil.copyData(o, PaperDTO.class);
    }

    /**
     * 删除试卷
     * @param paperDTO
     * @return
     */
    public Integer delete(PaperDTO paperDTO) {
        Example example = new Example(Paper.class);
        example.createCriteria().andEqualTo("id",paperDTO.getId())
                .andEqualTo("version",paperDTO.getVersion());
        return paperDao.delete(example);
    }

    /**
     * 批量删除试卷
     * @param paperDTOs
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer delete(List<PaperDTO> paperDTOs) {
        Example example = new Example(Paper.class);
        for (PaperDTO paperDTO : paperDTOs) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id", paperDTO.getId()).
                    andEqualTo("version", paperDTO.getVersion());
            example.or(criteria);
        }
        int result = paperDao.delete(example);
        if (result != paperDTOs.size()) {
            throw new BusinessException(PaperManageCodeEnum.MULTI_DELETE_EXCEPTION);
        }
        return result;
    }

    /**
     * 通过id查询试卷信息
     * @param paperDTO
     * @return
     */
    public PaperDTO getPaperById(PaperDTO paperDTO){
        return doPaperTransf(paperDao.getPaperById(paperDTO.getId()));
    }
    /**
     * 通过id查询试卷名称
     * @param id
     * @return
     */
    public String getPaperNameById(@RequestParam("id") Long id){
        Paper paper = paperDao.getPaperById(id);
        return paper.getPaperName();
    }

    /**
     * 更新试卷信息
     * @param paperDTO
     * @return
     */
    public Integer updatePaper(PaperDTO paperDTO){
        paperDTO.setVersion(paperDTO.getVersion()+1);
        Paper paper = BeanUtil.copyData(paperDTO, Paper.class);
        return paperDao.updatePaper(paper);
    }

    /**
     * 只用于更改试卷分数
     * @param paperDTO
     * @return
     */
    public Integer updatePaperWithoutVersion(PaperDTO paperDTO){
        Date date = new Date();
        paperDTO.setUpdatedTime(date);
        return paperDao.updatePaper(BeanUtil.copyData(paperDTO,Paper.class));
    }

    /**
     * 根据试卷id，发布次数+1 ，状态改成已发布
     * @param id
     * @return
     */
    public Integer updatePaperStateAndPublishTimes(Long id){
        Paper paper1 = paperDao.getPaperById(id);
        paper1.setPublishTimes(paper1.getPublishTimes()+1);
        paper1.setStatus(5);
        paper1.setVersion(paper1.getVersion()+1);
        return paperDao.updatePaper(paper1);
    }
    @Override
    public List<?> query(PaperDTO paperDTO) {
        return null;
    }

    @Override
    protected Paper doObjectTransf(PaperDTO paperDTO) {
        return BeanUtil.copyData(paperDTO, Paper.class);
    }

    /**
     * 添加试卷（模板组卷使用）
     * @param paperDTO
     * @return
     */
    public Integer insertPaper(PaperDTO paperDTO){
        // 获取模板的id
        Long templateId = paperDTO.getId();
        PaperSubjectDTO paperSubjectDTO = new PaperSubjectDTO();
        paperSubjectDTO.setPaperId(templateId);
        // 生成新的id
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        Long id = snowflake.nextId();
        // 查询得到模板对应试题信息
        List<PaperSubjectDTO> subjects = paperSubjectService.showSubjectsWithAnswers(paperSubjectDTO);
        for(PaperSubjectDTO paperSubjectDTO1:subjects){
            paperSubjectDTO1.setPaperId(id);
        }
        // 批量新增试题以及批量新增对应答案
        paperSubjectService.insertSubjectList(subjects);
        // 被使用次数+1
        PaperDTO paperDTO1 = new PaperDTO();
        paperDTO1.setId(templateId);
        paperDTO1.setPublishTimes(paperDTO.getPublishTimes()+1);
        // 更新试卷信息
        updatePaperWithoutVersion(paperDTO1);
        // 初始化某些初始值为固定值的字段
        paperDTO.setId(id);
        paperDTO.setTemplateTag(false);
        paperDTO.setStatus(0);
        paperDTO.setPublishTimes(0);
        paperDTO.setDownloadTimes(0);
        Date date = new Date();
        paperDTO.setCreatedTime(date);
        paperDTO.setUpdatedTime(date);
        paperDTO.setCombExamTime(date);
        paperDTO.setVersion(1L);
        // 插入试卷
        return paperDao.insert(BeanUtil.copyData(paperDTO,Paper.class));
    }

    /**
     * 快速组卷之添加试卷
     * @param paperDTO
     * @return
     */
    public Integer quickInsertPaper(PaperDTO paperDTO){
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        Long id = snowflake.nextId();
        paperId = id;
        paperDTO.setId(id);
        paperDTO.setTemplateTag(false);
        paperDTO.setStatus(0);
        paperDTO.setPublishTimes(0);
        paperDTO.setDownloadTimes(0);
        Date date = new Date();
        paperDTO.setCreatedTime(date);
        paperDTO.setUpdatedTime(date);
        paperDTO.setCombExamTime(date);
        paperDTO.setVersion(1L);
        return paperDao.insert(BeanUtil.copyData(paperDTO,Paper.class));
    }
    /**
     * 判断是否存在这个试卷名
     * @param paperDTO
     * @return
     */
    public Boolean hasSameNamePaper(PaperDTO paperDTO){
        Example example = new Example(Paper.class);
        example.createCriteria().andEqualTo("paperName",paperDTO.getPaperName())
                .andEqualTo("templateTag",false);
        Paper paper = paperDao.queryByExample(example);
        if(paper!=null){
            return true;
        }
        return false;
    }

    /**
     * 上传试卷为模板
     * @param paperDTO
     * @return
     */
    public Integer uploadPaper(PaperDTO paperDTO){
        // 获取试卷id
        Long paperId = paperDTO.getId();
        PaperSubjectDTO paperSubjectDTO = new PaperSubjectDTO();
        paperSubjectDTO.setPaperId(paperId);
        // 生成新的id
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        Long id = snowflake.nextId();
        // 查询得到试卷对应试题信息
        List<PaperSubjectDTO> subjects = paperSubjectService.showSubjectsWithAnswers(paperSubjectDTO);
        for(PaperSubjectDTO paperSubjectDTO1:subjects){
            paperSubjectDTO1.setPaperId(id);
        }
        // 批量新增试题以及批量新增对应答案
        paperSubjectService.insertSubjectList(subjects);
        // 改变试卷状态和版本
        PaperDTO paperDTO1 = new PaperDTO();
        paperDTO1.setId(paperId);
        paperDTO1.setStatus(1);
        paperDTO1.setVersion(paperDTO.getVersion()+1);
        updatePaperWithoutVersion(paperDTO1);
        // 设置参数并插入模板
        paperDTO.setId(id);
        paperDTO.setTemplateTag(true);
        paperDTO.setStatus(1);
        paperDTO.setPublishTimes(0);
        paperDTO.setDownloadTimes(0);
        Date date = new Date();
        paperDTO.setCreatedTime(date);
        paperDTO.setUpdatedTime(date);
        paperDTO.setCombExamTime(date);
        paperDTO.setVersion(1L);
        return paperDao.insert(BeanUtil.copyData(paperDTO,Paper.class));
    }

    /**
     * 下载模板为试卷
     * @param paperDTO
     * @return
     */
    public Integer downloadPaper(PaperDTO paperDTO){
        // 获取模板id
        Long templateId = paperDTO.getId();
        PaperSubjectDTO paperSubjectDTO = new PaperSubjectDTO();
        paperSubjectDTO.setPaperId(templateId);
        // 生成新的id
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        Long id = snowflake.nextId();
        // 查询得到模板对应试题信息
        List<PaperSubjectDTO> subjects = paperSubjectService.showSubjectsWithAnswers(paperSubjectDTO);
        for(PaperSubjectDTO paperSubjectDTO1:subjects){
            paperSubjectDTO1.setPaperId(id);
        }
        // 批量新增试题以及批量新增对应答案
        paperSubjectService.insertSubjectList(subjects);
        // 改变模板状态和版本
        PaperDTO paperDTO1 = new PaperDTO();
        paperDTO1.setId(templateId);
        paperDTO1.setStatus(1);
        paperDTO1.setDownloadTimes(paperDTO.getDownloadTimes()+1);
        paperDTO1.setVersion(paperDTO.getVersion()+1);
        updatePaperWithoutVersion(paperDTO1);
        // 设置参数并插入模板
        paperDTO.setId(id);
        paperDTO.setTemplateTag(false);
        paperDTO.setStatus(0);
        paperDTO.setPublishTimes(0);
        paperDTO.setDownloadTimes(0);
        Date date = new Date();
        paperDTO.setCreatedTime(date);
        paperDTO.setUpdatedTime(date);
        paperDTO.setCombExamTime(date);
        paperDTO.setVersion(1L);
        return paperDao.insert(BeanUtil.copyData(paperDTO,Paper.class));
    }
}
