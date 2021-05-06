import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout'

Vue.use(Router)

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: 'home',
        name: 'home',
        component: () => import('@/views/home/index'),
        meta: {title: '首页'}
      },
      {
        path: 'personInfo',
        name: 'personInfo',
        component: () => import('@/views/person_info'),
        meta: {title: '个人信息'}
      }
    ]
  },
  {
    path: '/resourcePermission',
    name: 'resourcePermission',
    component: Layout,
    redirect: '/roleManage',
    meta: {title: '资源权限'},
    children: [
      {
        path: 'roleManage',
        name: 'roleManage',
        component: () => import('@/views/resourcePermission/role_manage'),
        meta: {title: '角色管理'}
      },
      {
        path: 'permissionManage',
        name: 'permissionManage',
        component: () => import('@/views/resourcePermission/permission_manage'),
        meta: {title: '权限管理'}
      },
      {
        path: 'menuManage',
        name: 'menuManage',
        component: () => import('@/views/resourcePermission/menu_manage'),
        meta: {title: '菜单管理'}
      },
      {
        path: 'resourceManage',
        name: 'resourceManage',
        component: () => import('@/views/resourcePermission/resource_manage'),
        meta: {title: '资源管理'}
      }
    ]
  },
  {
    path: '/orgBuild',
    name: 'orgBuild',
    component: Layout,
    redirect: '/companyManage',
    meta: {title: '组织架构'},
    children: [
      {
        path: 'companyManage',
        name: 'companyManage',
        component: () => import('@/views/orgBuild/company_manage'),
        meta: {title: '公司管理'}
      },
      {
        path: 'deptManage',
        name: 'deptManage',
        component: () => import('@/views/orgBuild/dept_manage'),
        meta: {title: '部门管理'}
      },
      {
        path: 'personManage',
        name: 'personManage',
        component: () => import('@/views/orgBuild/person_manage'),
        meta: {title: '人员管理'}
      },
      {
        path: 'dutyManage',
        name: 'dutyManage',
        component: () => import('@/views/orgBuild/duty_manage'),
        meta: {title: '职位管理'}
      }
    ]
  },
  {
    path: '/systemManage',
    name: 'systemManage',
    component: Layout,
    redirect: '/appManage',
    meta: {title: '系统管理'},
    children: [
      {
        path: 'appManage',
        name: 'appManage',
        component: () => import('@/views/systemManage/app_manage'),
        meta: {title: '应用管理'}
      },
      {
        path: 'dictManage',
        name: 'dictManage',
        component: () => import('@/views/systemManage/dict_manage'),
        meta: {title: '字典管理'}
      }
    ]
  },
  {
    path: '/logManager',
    name: 'logManager',
    component: Layout,
    redirect: '/bizLog',
    meta: {title: '日志管理'},
    children: [
      {
        path: 'bizLog',
        name: 'bizLog',
        component: () => import('@/views/logManager/biz_log'),
        meta: {title: '业务日志'}
      }
    ]
  },
  {
    path: '/apiManager',
    name: 'apiManager',
    component: Layout,
    redirect: '/swagger',
    meta: {title: '接口文档'},
    children: [
      {
        path: 'swagger',
        name: 'swagger',
        component: () => import('@/views/api/swagger'),
        meta: {title: 'swagger文档'}
      }
    ]
  },
  {
    path: '/configs',
    name: 'configs',
    component: Layout,
    redirect: '/nacos',
    meta: {title: '参数配置'},
    children: [
      {
        path: 'nacos',
        name: 'nacos',
        component: () => import('@/views/config/nacos-config'),
        meta: {title: '配置中心'}
      }
    ]
  },
  {
    path: '/monitors',
    name: 'monitors',
    component: Layout,
    redirect: '/druid',
    meta: {title: '系统监控'},
    children: [
      {
        path: 'druid',
        name: 'druid',
        component: () => import('@/views/monitor/druid'),
        meta: {title: 'druid监控'}
      },
      {
        path: 'hardware',
        name: 'hardware',
        component: () => import('@/views/monitor/hardware'),
        meta: {title: '硬件信息'}
      }
    ]
  },
  {
    path: '/multiDatasource',
    name: 'multiDatasource',
    component: Layout,
    redirect: '/dsManage',
    meta: {title: '多数据源'},
    children: [
      {
        path: 'dsManage',
        name: 'dsManage',
        component: () => import('@/views/multiDatasource/dsManage'),
        meta: {title: '数据源管理'}
      }
    ]
  },
  {
    path: '/tenant',
    name: 'tenant',
    component: Layout,
    redirect: '/tenantManager',
    meta: {title: 'SaaS租户'},
    children: [
      {
        path: 'tenantManager',
        name: 'tenantManager',
        component: () => import('@/views/tenant/tenantManager'),
        meta: {title: '多租户管理'}
      }
    ]
  },
  {
    path: '/testMenu',
    name: 'testMenu',
    component: Layout,
    redirect: '/testMenu/index',
    meta: {title: '每日下载记录'},
    children: [
      {
        path: 'index',
        name: 'index',
        component: () => import('@/views/testMenu/index'),
        meta: {title: '每日下载记录'}
      }
    ]
  },
  {
    path: '/testMenu',
    name: 'testMenu',
    component: Layout,
    redirect: '/testMenu/AbnormalSettlement',
    meta: {title: '异常结算单记录'},
    children: [
      {
        path: 'AbnormalSettlement',
        name: 'AbnormalSettlement',
        component: () => import('@/views/testMenu/AbnormalSettlement'),
        meta: {title: '异常结算单记录'}
      }
    ]
  },
  {
    path: '/testMenu',
    name: 'testMenu',
    component: Layout,
    redirect: '/testMenu/TaskAuditSettlement',
    meta: {title: 'settlement报告审核'},
    children: [
      {
        path: 'TaskAuditSettlement',
        name: 'TaskAuditSettlement',
        component: () => import('@/views/testMenu/TaskAuditSettlement'),
        meta: {title: 'settlement报告审核'}
      }
    ]
  },
  {
    path: '/dataSource',
    name: 'dataSource',
    component: Layout,
    redirect: '/dataSource2',
    meta: {title: '集成中心'},
    children: [
      {
        path: 'dataSource2',
        name: 'dataSource2',
        component: () => import('@/views/dataSource/index'),
        meta: {title: '数据源配置'}
      }
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]

const createRouter = () => new Router({
  scrollBehavior: () => ({y: 0}),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
