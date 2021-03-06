package cn.stylefeng.guns.cloud.system.core.dbs.dao.sqls;


import cn.stylefeng.guns.cloud.model.enums.DbIdEnum;

/**
 * 异构sql获取
 *
 * @author fengshuonan
 * @date 2019-07-16-13:13
 */
public abstract class AbstractSql {

    /**
     * 获取异构sql
     *
     * @author fengshuonan
     * @date 2019-07-16 13:14
     */
    public String getSql(String jdbcUrl) {
        if (jdbcUrl.contains(DbIdEnum.ORACLE.getName())) {
            return oracle();
        } else if (jdbcUrl.contains(DbIdEnum.PG_SQL.getName())) {
            return pgSql();
        } else if (jdbcUrl.contains(DbIdEnum.MS_SQL.getName())) {
            return sqlServer();
        } else {
            return mysql();
        }
    }

    /**
     * 获取mysql
     *
     * @return java.lang.String
     * @author xuyuxiang
     * @date 2020/9/1 14:50
     **/
    protected abstract String mysql();

    /**
     * 获取sqlServer
     *
     * @return java.lang.String
     * @author xuyuxiang
     * @date 2020/9/1 14:50
     **/
    protected abstract String sqlServer();

    /**
     * 获取pgSql
     *
     * @return java.lang.String
     * @author xuyuxiang
     * @date 2020/9/1 14:50
     **/
    protected abstract String pgSql();

    /**
     * 获取oracle
     *
     * @return java.lang.String
     * @author xuyuxiang
     * @date 2020/9/1 14:50
     **/
    protected abstract String oracle();

}
