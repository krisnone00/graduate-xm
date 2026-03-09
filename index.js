import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
    //嵌套路由 在嵌套路由里面，子路由不用写前面的斜杠 / 会自动添加
  routes: [
      { path: '/', redirect: '/cyberlogin' },
      { path: '/manager', component: () => import('../views/Manager.vue'), children: [
          { path: 'home',   meta: { title: '主页'},  component: () => import('../views/Home.vue')},  //   /manager/home
          { path: 'test',   meta: { title: '测试数据页面'}, component: () => import('../views/Test.vue')},
          { path: 'category',   meta: { title: '商品分类' }, component: () => import('../views/Category.vue')},
          { path: 'product',   meta: { title: '商品' }, component: () => import('../views/Product/Product.vue')},
          { path: 'brand', meta: { title: '品牌管理' }, component: () => import('../views/Brand.vue') },
          { path: 'admin',   meta: { title: '管理员信息'}, component: () => import('../views/Admin.vue')},
          { path: 'employee',   meta: { title: '员工信息'}, component: () => import('../views/Employee.vue')},
          { path: 'password',   meta: { title: '修改密码'}, component: () => import('../views/Password.vue')},
          { path: 'person',  meta: { title: '个人信息'}, component: () => import('../views/Person.vue')},
        ]},
      { path: '/login',  name: 'Login', meta: { title: '登录系统'}, component: () => import('../views/Login.vue')},
      { path: '/register',  name: 'Register', meta: { title: '欢迎注册'}, component: () => import('../views/RegisterTest.vue')},
      { path: '/netflixanimation',  name: 'NetflixAnimation', meta: { title: '欢迎注册'}, component: () => import('../views/NetflixAnimation.vue')},
      { path: '/welcomeanimation',  name: 'WelcomeAnimation', meta: { title: '欢迎注册'}, component: () => import('../views/WelcomeAnimation.vue')},
      { path: '/cyberlogin',  name: 'CyberLogin', meta: { title: '登录'}, component: () => import('../views/CyberLogin.vue')},
      { path: '/notfound404',  meta: { title: '404找不到页面'}, component: () => import('../views/NotFound404_2nd.vue')},
      { path: '/:pathMatch(.*)', redirect: '/notfound404' },  //配置404页面，必须放在最后面
  ]
})

//beforeEach表示跳转之前的一些操作
router.beforeEach((to, from, next) => {
  document.title = to.meta.title  //设置页面标题
  next()  //放行    必须调用这个函数才能实现跳转
})

export default router
