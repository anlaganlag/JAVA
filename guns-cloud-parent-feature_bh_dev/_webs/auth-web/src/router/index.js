import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'
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
        meta: { title: '首页' }
      },
      {
        path: 'personInfo',
        name: 'personInfo',
        component: () => import('@/views/person_info'),
        meta: { title: '个人信息' }
      },
    ]
  },
  {
    path: "/ssoApp",
    name: "ssoApp",
    component: Layout,
    redirect: "/ssoApp/clientManage",
    children: [
      {
        path: "clientManage",
        name: "clientManage",
        component: () => import("@/views/ssoApp/client_manage"),
        meta: {
          keepAlive: true,
          title: "客户端管理",
        }
      },
    ],
    meta: {
      keepAlive: true,
      title: "单点应用",
    }
  },
  {
    path: "/securityAudit",
    name: "securityAudit",
    component: Layout,
    redirect: "/securityAudit/loginLog",
    children: [
      {
        path: "loginLog",
        name: "loginLog",
        component: () => import("@/views/securityAudit/login_log"),
        meta: {
          keepAlive: true,
          title: "登录日志",
        }
      },
      {
        path: "sessionManage",
        name: "sessionManage",
        component: () => import("@/views/securityAudit/session_manage"),
        meta: {
          keepAlive: true,
          title: "会话管理",
        }
      },
      {
        path: "godKeyPassword",
        name: "godKeyPassword",
        component: () => import("@/views/securityAudit/godKeyPassword"),
        meta: {
          keepAlive: true,
          title: "万能密码",
        }
      },
    ],
    meta: {
      keepAlive: true,
      title: "安全审计",
    }
  },
  {
    path: "/resourceMaintain",
    name: "resourceMaintain",
    component: Layout,
    redirect: "/resourceMaintain/resourceMaintainIndex",
    children: [
      {
        path: "resourceMaintainIndex",
        name: "resourceMaintainIndex",
        component: () => import("@/views/resourceMaintain/resource_maintain_index"),
        meta: {
          keepAlive: true,
          title: "资源维护",
        }
      },
    ],
    meta: {
      keepAlive: true,
      title: "资源维护",
    }
  },
  {
    path: "/routingMaintenance",
    name: "routingMaintenance",
    component: Layout,
    redirect: "/routingMaintenance/routingMaintenanceIndex",
    children: [
      {
        path: "routingMaintenanceIndex",
        name: "routingMaintenanceIndex",
        component: () => import("@/views/routingMaintenance/routing_maintenance_index"),
        meta: {
          keepAlive: true,
          title: "网关路由列表",
        }
      },
    ],
    meta: {
      keepAlive: true,
      title: "网关路由列表",
    }
  },
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
