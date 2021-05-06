package cn.stylefeng.guns.cloud.system.modular.ent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.core.dbs.annotation.DataSource;
import cn.stylefeng.guns.cloud.system.core.util.CodeUtil;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompApp;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompany;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntJob;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntCompanyMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntJobMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntCompanyParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntJobParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntCompanyResult;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntJobResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntCompAppService;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntCompanyService;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntJobService;
import cn.stylefeng.guns.cloud.system.modular.ent.utils.EntUtil;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysApp;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysAppService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.stylefeng.guns.cloud.model.enums.StatusEnum.DISABLE;
import static cn.stylefeng.guns.cloud.system.modular.ent.common.EntConst.ROOT_ID;
import static cn.stylefeng.guns.cloud.system.modular.ent.common.EntConst.ROOT_PID;
import static cn.stylefeng.guns.cloud.system.modular.ent.enums.EntException.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * <p>
 * 企业信息 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
@Service
public class EntJobServiceImpl extends ServiceImpl<EntJobMapper, EntJob> implements EntJobService {

    @DataSource(name = "EBMS")
    @Override
    public PageResult<EntJobResult> findPageBySpec(EntJobParam param) {
        Page pageContext = getPageContext();
        IPage<EntJobResult> page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult<>(page);
    }


    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

}
