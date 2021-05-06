package cn.stylefeng.guns.cloud.system.modular.ent.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.core.dbs.annotation.DataSource;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompany;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntTaskAudit;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntTaskAuditMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntAbnormalParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntTaskAuditParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntAbnormalResult;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntTaskAuditResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntTaskAuditService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import static cn.stylefeng.guns.cloud.model.enums.StatusEnum.DISABLE;
import static cn.stylefeng.guns.cloud.system.modular.ent.enums.EntException.CHECKED_FAILED;

/**
 * <p>
 * 企业信息 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
@Service
public class EntTaskAuditServiceImpl extends ServiceImpl<EntTaskAuditMapper, EntTaskAudit> implements EntTaskAuditService {

    @DataSource(name = "EBMS")
    @Override
    public PageResult<EntTaskAuditResult> findPageBySpec(EntTaskAuditParam param) {
        Page pageContext = getPageContext();
        IPage<EntTaskAuditResult> page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult<>(page);
    }

    /**
     * 公司启用禁用
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    @DataSource(name = "EBMS")
    @Override
    public void changeAuditStatus(Long Id, String auditstatus) {
        if (ObjectUtil.isEmpty(Id)) {
            throw new RequestEmptyException("Id为空");
        }
        if (auditstatus == null) {
            throw new RequestEmptyException("审核状态为空");
        }
        if (auditstatus == "已审核") {
            //禁用操作
            int sonCount = this.count(new QueryWrapper<EntTaskAudit>()
                    .eq("id", 41)
                    .eq("auditstatus", auditstatus));
            if (sonCount != 0) {
                throw new ServiceException(CHECKED_FAILED);
            }
        }
        EntTaskAudit company = this.getById(Id);
        company.setAuditstatus(auditstatus);
        this.updateById(company);
    }



    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

}
