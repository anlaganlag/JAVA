/*
Copyright [2020] [https://www.stylefeng.cn]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Guns采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：

1.请不要删除和修改根目录下的LICENSE文件。
2.请不要删除和修改Guns源码头部的版权声明。
3.请保留源码和相关描述文件的项目出处，作者声明等。
4.分发源码时候，请注明软件出处 https://gitee.com/stylefeng/guns-separation
5.在修改包名，模块名称，项目代码等时，请注明软件出处 https://gitee.com/stylefeng/guns-separation
6.若您的项目无法满足以上几点，可申请商业授权，获取Guns商业授权许可，请在官网购买授权，地址为 https://www.stylefeng.cn
 */
package cn.stylefeng.guns.cloud.system.core.dbs.listener;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.Log;
import cn.stylefeng.guns.cloud.libs.config.properties.DruidProperties;
import cn.stylefeng.guns.cloud.system.core.dbs.context.DataSourceContext;
import cn.stylefeng.guns.cloud.system.core.dbs.exception.DataSourceException;
import cn.stylefeng.guns.cloud.system.core.dbs.exception.enums.DataSourceExceptionEnum;
import cn.stylefeng.guns.cloud.system.core.dbs.factory.DruidFactory;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;


/**
 * 多数据源的初始化，加入到数据源Context中的过程
 *
 * @author fengshuonan
 * @date 2020/8/25
 */
public class MultiDatasourceInitListener implements ApplicationListener<ApplicationContextInitializedEvent>, Ordered {

    private static final Log log = Log.get();

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent applicationContextInitializedEvent) {

        ConfigurableApplicationContext applicationContext = applicationContextInitializedEvent.getApplicationContext();

        // 如果是配置中心的上下文就略过
        if (applicationContext instanceof AnnotationConfigApplicationContext) {
            return;
        }

        ConfigurableEnvironment environment = applicationContextInitializedEvent.getApplicationContext().getEnvironment();

        // 获取数据库连接配置
        String dataSourceDriver = environment.getProperty("spring.datasource.driver-class-name");
        String dataSourceUrl = environment.getProperty("spring.datasource.url");
        String dataSourceUsername = environment.getProperty("spring.datasource.username");
        String dataSourcePassword = environment.getProperty("spring.datasource.password");

        // 如果有为空的配置，终止执行
        if (ObjectUtil.hasEmpty(dataSourceUrl, dataSourceUsername, dataSourcePassword)) {
            throw new DataSourceException(DataSourceExceptionEnum.DATA_SOURCE_READ_ERROR);
        }

        // 创建主数据源的properties
        DruidProperties druidProperties = new DruidProperties();
        druidProperties.setDriverClassName(dataSourceDriver);
        druidProperties.setUrl(dataSourceUrl);
        druidProperties.setUsername(dataSourceUsername);
        druidProperties.setPassword(dataSourcePassword);

        // 创建主数据源
        DruidDataSource druidDataSource = DruidFactory.createDruidDataSource(druidProperties);

        // 初始化数据源容器
        try {
            DataSourceContext.initDataSource(druidProperties, druidDataSource);
        } catch (Exception e) {
            log.error(">>> 初始化数据源容器错误!", e);
            throw new DataSourceException(DataSourceExceptionEnum.INIT_DATA_SOURCE_ERROR);
        }

    }
}
