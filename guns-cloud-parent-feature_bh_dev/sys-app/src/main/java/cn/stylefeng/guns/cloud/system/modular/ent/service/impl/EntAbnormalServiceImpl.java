package cn.stylefeng.guns.cloud.system.modular.ent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.core.dbs.annotation.DataSource;
import cn.stylefeng.guns.cloud.system.core.util.CodeUtil;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.*;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntAbnormalMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntJobMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntAbnormalParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntJobParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntUserDeptParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntUserParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntAbnormalResult;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntJobResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntAbnormalService;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntJobService;
import cn.stylefeng.guns.cloud.system.modular.ent.utils.EntUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 企业信息 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
@Service
public class EntAbnormalServiceImpl extends ServiceImpl<EntAbnormalMapper, EntAbnormal> implements EntAbnormalService {

    @DataSource(name = "EBMS")
    @Override
    public PageResult<EntAbnormalResult> findPageBySpec(EntAbnormalParam param) {
        Page pageContext = getPageContext();
        IPage<EntAbnormalResult> page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult<>(page);
    }
    /**
     * 新增用户信息
     *
     * @author stylefeng
     * @date 2019/10/15
     */
    @DataSource(name = "EBMS")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(EntAbnormalParam param) throws ParseException {

        EntAbnormalParam search = new EntAbnormalParam();
        search.setPlatname(param.getPlatname());
        search.setShopname(param.getShopname());
        search.setSite(param.getSite());
        Page pageContext = getPageContext();
        IPage<EntAbnormalResult> page = this.baseMapper.customPageList(pageContext, param);

        if(page.getTotal()>0){
             //返回提示已存在
        }else{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dd = format.parse(param.getStartdate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.DAY_OF_MONTH, 14);
            String T3 = format.format(calendar.getTime() ) ;
            param.setEnddate(T3);
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            String T4 = format.format(calendar.getTime() ) ;
            param.setDepositdate(T4);

            EntAbnormal entity = getEntity(param);
            EntAbnormal entity2 = new EntAbnormal();
            this.save(entity);
        }
    }



    private EntAbnormal getEntity(EntAbnormalParam param) {
        EntAbnormal entity = new EntAbnormal();
        BeanUtil.copyProperties(param, entity);
        return entity;
    }
    private Page getPageContext() {
        return PageFactory.defaultPage();
    }
}
