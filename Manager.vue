<template>
  <div
      class="commander-terminal boot-process"
      :class="[isDark ? 'mode-onyx' : 'mode-titanium']"
  >

    <div class="global-env">
      <div class="void-bg"></div>
      <div class="dynamic-grid"></div>
      <div class="ambient-pulse"></div>
      <div class="initial-scan-laser"></div>
    </div>

    <div class="tactical-switch-container" @click="toggleTheme">
      <div class="switch-housing">
        <div class="switch-label">VISUAL_MODE</div>
        <div class="switch-track">
          <div class="switch-piston" :class="{ 'active': !isDark }"></div>
          <div class="track-decor">//////</div>
        </div>
        <div class="switch-status">{{ isDark ? 'STEALTH' : 'DAYLIGHT' }}</div>
      </div>
    </div>

    <aside class="master-sidebar" :class="{ 'is-collapsed': isCollapsed }">
      <div class="tactical-rail" @click="isCollapsed = !isCollapsed">
        <div class="rail-actuator"><div class="actuator-led"></div></div>
      </div>

      <div class="brand-unit boot-flicker">
        <div class="fixed-slot-75"><div class="brand-shell">S</div></div>
        <transition name="meta-glide">
          <div class="brand-info" v-if="!isCollapsed">
            <h1 class="title">SYSTEM</h1><span class="version">CORE_V290</span>
          </div>
        </transition>
      </div>

      <div class="identity-capsule boot-flicker" style="animation-delay: 0.1s;">
        <div class="fixed-slot-75">
          <div class="avatar-holo"><div class="holo-scan-bar"></div><div class="holo-orbit"></div><span class="initial">{{ user.username?.charAt(0).toUpperCase() }}</span></div>
        </div>
        <transition name="meta-glide">
          <div class="id-meta" v-if="!isCollapsed">
            <div class="name">{{ user.username }}</div><div class="status-msg">:: NODE_STABLE</div>
          </div>
        </transition>
      </div>

      <nav class="nav-matrix">
        <div v-for="(item, index) in menuItems" :key="item.path" class="nav-node boot-flicker" :style="{ animationDelay: (index * 0.1 + 0.2) + 's' }" :class="{ 'node-active': $route.path === item.path }" @click="$router.push(item.path)">
          <div class="fixed-slot-75"><div class="icon">{{ item.icon }}</div></div>
          <transition name="meta-glide"><div class="label-box" v-if="!isCollapsed"><span class="cn">{{ item.label }}</span><span class="en">CHANNEL_0{{ index + 1 }}</span></div></transition>
          <div class="active-plasma-line"></div>
        </div>
      </nav>

      <div class="sidebar-footer boot-flicker" style="animation-delay: 0.8s;">
        <div class="logout-panel" @click="logout">
          <div class="plasma-wiper"></div>
          <div class="fixed-slot-75" style="z-index: 2;"><span class="sym">⏻</span></div>
          <transition name="meta-glide"><span class="label" v-if="!isCollapsed" style="z-index: 2;">SYSTEM_DISCONNECT</span></transition>
        </div>
      </div>
    </aside>

    <main class="tactical-canvas">

      <header class="canvas-header boot-flicker" style="animation-delay: 0.4s;">
        <div class="breadcrumb-spine">
          <span class="root">ARCH_CONTROL</span><span class="sep">//</span><span class="current">{{ currentPathName }}</span>
        </div>
        <div class="header-tools">
          <div class="monitor-data">
            <span class="mon-item">LATENCY: <span class="accent">0.1ms</span></span>
            <span class="mon-item">SESSION: <span class="accent">{{ sessionID }}</span></span>
          </div>
          <div style="width: 120px;"></div>
        </div>
      </header>

      <section class="stage-container boot-view-burst">
        <div class="bezel-bracket tl"></div><div class="bezel-bracket tr"></div>
        <div class="bezel-bracket bl"></div><div class="bezel-bracket br"></div>

        <div class="view-content-box">
          <router-view />
        </div>
      </section>

      <footer class="canvas-footer boot-flicker" style="animation-delay: 1.0s;">
        <div class="footer-line"></div>
        <div class="footer-hard-info">
          <div class="info-block"><span class="label">SYS_STATUS</span><span class="val-ok">● ONLINE</span></div>
          <div class="info-block"><span class="label">MEM_USAGE</span><span class="val">42%</span></div>
          <div class="info-block"><span class="label">SECURITY</span><span class="val-lock">LOCKED</span></div>
        </div>
      </footer>
    </main>

    <div class="photon-flash" :class="{ 'trigger': flashActive }"></div>

  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessageBox } from 'element-plus'

const route = useRoute()
const router = useRouter()
const isCollapsed = ref(false)
const flashActive = ref(false)
const user = JSON.parse(localStorage.getItem('bs-xm-user') || '{}')
const sessionID = ref('V290-' + Math.floor(Math.random() * 899 + 100))

// 核心修复：刷新保持极昼模式
const isDark = ref(localStorage.getItem('bs-xm-theme') !== 'light')

// 侧边栏导航菜单配置
const menuItems = ref([
  {
    title: '指挥中心',
    sub: 'DASHBOARD',
    path: '/manager/home',
    icon: '⌬'  // 苯环符号，代表核心
  },
  {
    title: '物资分类',
    sub: 'CATEGORY',
    path: '/manager/category',
    icon: '∷'  // 矩阵点，代表分类
  },
  {
    title: '品牌协议',
    sub: 'BRAND_NET',
    path: '/manager/brand',
    icon: '©'  // 版权符号，代表品牌
  },
  {
    title: '供应节点',
    sub: 'SUPPLIER',
    path: '/manager/supplier',
    icon: '⊞'  // 窗口符号，代表外部接口（新增！）
  },
  {
    title: '库存矩阵',
    sub: 'INVENTORY',
    path: '/manager/product',
    icon: '◬'  // 三角警示，代表核心资产
  },
  {
    title: '采购入库',
    sub: 'INBOUND',
    path: '/manager/inbound',
    icon: '⇲'  // 下沉箭头，代表入库（新增！）
  },
  {
    title: '人员档案',
    sub: 'PERSONNEL',
    path: '/manager/employee',
    icon: '☫'  // 辐射符号，代表人力
  },
  {
    title: '权限核心',
    sub: 'ADMIN_ROOT',
    path: '/manager/admin',
    icon: '⚔'  // 交叉剑，代表管理权限
  },
  {
    title: '个人链路',
    sub: 'PROFILE',
    path: '/manager/person',
    icon: '⧇'  // 几何体，代表个人
  }
])

const currentPathName = computed(() => {
  // 第一层防线：确保 menuItems 存在且确实是一个数组
  if (!menuItems.value || !Array.isArray(menuItems.value)) {
    return 'WORKSPACE'
  }

  // 第二层防线：使用你之前的 find 逻辑，并增加默认值
  const target = menuItems.value.find(i => i.path === route.path)
  return target ? target.label : 'WORKSPACE'
})

const toggleTheme = () => {
  flashActive.value = true
  isDark.value = !isDark.value
  localStorage.setItem('bs-xm-theme', isDark.value ? 'dark' : 'light')
  setTimeout(() => { flashActive.value = false }, 200)
}

const logout = () => {
  ElMessageBox.confirm('是否执行断开程序？', '系统校验', {
    confirmButtonText: '确认', cancelButtonText: '取消', type: 'warning'
  }).then(() => {
    localStorage.removeItem('bs-xm-user')
    router.push('/cyberlogin')
  })
}
</script>

<style scoped>
@import url('https://gs.jurieo.com/gemini/fonts-googleapis/css2?family=Audiowide&family=Rajdhani:wght@600;700&family=Share+Tech+Mono&display=swap');

/* 针对你自定义的 nav-matrix 结构进行适配 */
.nav-node .icon {
  color: var(--accent); /* 核心发光色 */
  text-shadow: 0 0 10px var(--accent);
  font-size: 1.2rem;
  width: 30px;
  text-align: center;
}

/* 激活状态下的特殊发光（呼应你的 active-plasma-line） */
.nav-node.node-active .icon {
  color: var(--accent);
  filter: brightness(1.2); /* 增加亮度，让变色效果更明显 */
}

/* 变量保持不变... */
.mode-onyx {
  --bg-color: #050505; --side-bg: rgba(10, 10, 10, 0.85); --text-main: #ffffff; --text-dim: #555555; --accent: #00f3ff; --accent-glow: rgba(0, 243, 255, 0.4); --border-ui: rgba(255, 255, 255, 0.08); --hover-bg: rgba(0, 243, 255, 0.08); --stage-bg: rgba(10, 10, 10, 0.5); --sw-bg: #111; --sw-border: #333; --sw-piston: #00f3ff; --sw-text: #00f3ff;
}
.mode-titanium {
  --bg-color: #f4f7fa; --side-bg: rgba(255, 255, 255, 0.92); --text-main: #121212; --text-dim: #999999; --accent: #0055ff; --accent-glow: rgba(0, 85, 255, 0.15); --border-ui: rgba(0, 0, 0, 0.06); --hover-bg: rgba(0, 85, 255, 0.05); --stage-bg: rgba(255, 255, 255, 0.6); --sw-bg: #fff; --sw-border: #ccc; --sw-piston: #0055ff; --sw-text: #0055ff;
}

@keyframes global-hue-shift { 0% { filter: hue-rotate(0deg); } 100% { filter: hue-rotate(360deg); } }

.commander-terminal {
  display: flex; width: 100vw; height: 100vh;
  background-color: var(--bg-color); color: var(--text-main);
  font-family: 'Rajdhani', sans-serif;
  animation: global-hue-shift 60s infinite linear;
  overflow: hidden; transition: 0.5s cubic-bezier(0.4, 0, 0.2, 1);
}

/* 🌟 Sidebar 核心修复：flex-shrink: 0 防止被挤压 🌟 */
.master-sidebar {
  position: relative; z-index: 20;
  width: 275px;
  min-width: 275px; /* 双重保险 */
  height: 100%;
  flex-shrink: 0;   /* 彻底禁止收缩！ */
  background: var(--side-bg); border-right: 1px solid var(--border-ui); backdrop-filter: blur(30px); display: flex; flex-direction: column; transition: 0.5s cubic-bezier(0.19, 1, 0.22, 1);
}
.master-sidebar.is-collapsed { width: 75px; min-width: 75px; }

/* 🌟 Main Canvas 核心修复：min-width: 0 防止表格撑破 🌟 */
.tactical-canvas {
  flex: 1;
  display: flex; flex-direction: column;
  z-index: 5;
  min-width: 0; /* Flex布局防溢出魔法 */
  overflow: hidden; /* 防止内部滚动条影响外部 */
}

/* 🌟 Stage Container 核心修复：布局与边距 🌟 */
.stage-container {
  flex: 1;
  /* 上下 20px，左右 40px。这里的 40px 就是你想要的“右边留空” */
  margin: 20px 40px;
  position: relative;
  background: var(--stage-bg);
  border: 1px solid var(--border-ui);
  transition: 0.5s;
  box-shadow: inset 0 0 40px rgba(0,0,0,0.05);
  display: flex; /* 让内部 box 填满 */
  flex-direction: column;
  overflow: hidden; /* 确保内容不溢出边框 */
}

/* 🌟 View Content Box 核心修复：滚动条稳定 + Padding 🌟 */
.view-content-box {
  width: 100%;
  height: 100%;
  padding: 35px; /* 内部再留 35px 呼吸空间 */
  overflow-y: auto;
  scrollbar-gutter: stable; /* 预留滚动条位置，防止画面抖动 */
  box-sizing: border-box; /* 确保 padding 算在宽度内 */
}

/* 开关样式 (保持你喜欢的机械风) */
.tactical-switch-container {
  position: fixed; top: 15px; right: 30px; z-index: 9999; cursor: pointer; perspective: 800px;
}
.switch-housing {
  background: var(--sw-bg); border: 1px solid var(--sw-border); padding: 5px 10px; display: flex; flex-direction: column; align-items: flex-end; gap: 2px; box-shadow: 0 10px 30px rgba(0,0,0,0.3); transition: 0.3s; clip-path: polygon(10px 0, 100% 0, 100% calc(100% - 10px), calc(100% - 10px) 100%, 0 100%, 0 10px);
}
.switch-housing:hover { border-color: var(--accent); box-shadow: 0 0 15px var(--accent-glow); }
.switch-label { font-family: 'Share Tech Mono'; font-size: 0.55rem; color: var(--text-dim); letter-spacing: 1px; }
.switch-track { width: 100px; height: 18px; background: rgba(0,0,0,0.2); border: 1px solid var(--border-ui); position: relative; overflow: hidden; }
.track-decor { position: absolute; top: 0; left: 0; width: 100%; height: 100%; display: flex; align-items: center; justify-content: center; color: var(--border-ui); font-size: 0.6rem; letter-spacing: 3px; pointer-events: none; }
.switch-piston { width: 50%; height: 100%; background: var(--text-dim); transition: transform 0.3s cubic-bezier(0.23, 1, 0.32, 1); opacity: 0.5; }
.switch-piston.active { transform: translateX(100%); background: var(--sw-piston); opacity: 1; box-shadow: 0 0 10px var(--sw-piston); }
.switch-status { font-family: 'Audiowide'; font-size: 0.7rem; color: var(--sw-text); margin-top: 2px; }

/* 其他样式保持不变 */
.global-env { position: absolute; width: 100%; height: 100%; z-index: 0; pointer-events: none; }
.void-bg { position: absolute; width: 100%; height: 100%; background: var(--bg-color); }
.dynamic-grid { position: absolute; width: 100%; height: 100%; background-image: radial-gradient(var(--text-main) 1px, transparent 1px); background-size: 50px 50px; opacity: 0.02; }
.ambient-pulse { position: absolute; width: 1000px; height: 1000px; background: radial-gradient(circle at 10% 50%, var(--accent), transparent 70%); opacity: 0.04; }
.tactical-rail { position: absolute; right: -25px; top: 0; width: 35px; height: 100%; cursor: pointer; z-index: 100; display: flex; align-items: center; justify-content: center; }
.rail-actuator { width: 2px; height: 80px; background: var(--border-ui); transition: 0.4s; }
.actuator-led { width: 4px; height: 30px; background: var(--accent); box-shadow: 0 0 10px var(--accent); position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); transition: 0.3s; }
.tactical-rail:hover .rail-actuator { height: 140px; width: 4px; background: var(--accent); }
.fixed-slot-75 { width: 75px; min-width: 75px; flex-shrink: 0; display: flex; justify-content: center; align-items: center; }
.brand-unit { height: 90px; display: flex; align-items: center; border-bottom: 1px solid var(--border-ui); overflow: hidden; }
.brand-shell { width: 35px; height: 35px; background: var(--text-main); color: var(--bg-color); font-family: 'Audiowide'; font-weight: 900; font-size: 1.4rem; display: flex; align-items: center; justify-content: center; border-radius: 4px; box-shadow: 0 0 15px var(--accent-glow); transition: 0.5s; }
.brand-info { white-space: nowrap; }
.brand-info .title { font-family: 'Audiowide'; font-size: 1.15rem; margin: 0; letter-spacing: 2px; }
.brand-info .version { font-family: 'Share Tech Mono'; font-size: 0.6rem; color: var(--accent); opacity: 0.6; }
.identity-capsule { padding: 30px 0; display: flex; align-items: center; overflow: hidden; }
.avatar-holo { position: relative; width: 46px; height: 46px; display: flex; align-items: center; justify-content: center; border: 1px solid var(--border-ui); border-radius: 50%; }
.holo-orbit { position: absolute; width: 120%; height: 120%; border: 1px dashed var(--accent); border-radius: 50%; animation: spin 10s linear infinite; opacity: 0.3; }
.holo-scan-bar { position: absolute; width: 100%; height: 2px; background: var(--accent); box-shadow: 0 0 8px var(--accent); animation: scan-v 4s infinite ease-in-out; }
.avatar-holo .initial { font-family: 'Audiowide'; font-size: 1.3rem; }
.id-meta { white-space: nowrap; }
.id-meta .name { font-weight: 700; font-size: 1rem; }
.id-meta .status-msg { font-family: 'Share Tech Mono'; font-size: 0.65rem; color: var(--accent); opacity: 0.7; }
.nav-matrix { flex: 1; display: flex; flex-direction: column; gap: 4px; padding: 10px 0; overflow-y: auto; overflow-x: hidden; }
.nav-node { height: 60px; min-height: 60px; display: flex; align-items: center; cursor: pointer; transition: 0.3s; color: var(--text-dim); position: relative; }
.nav-node:hover { background: var(--hover-bg); color: var(--text-main); }
.nav-node.node-active { background: var(--hover-bg); color: var(--text-main); }
.nav-node.node-active .icon { color: var(--accent); text-shadow: 0 0 15px var(--accent); }
.active-plasma-line { position: absolute; left: 0; width: 4px; height: 0; background: var(--accent); transition: 0.3s; }
.node-active .active-plasma-line { height: 65%; box-shadow: 0 0 15px var(--accent); }
.sidebar-footer { height: 85px; min-height: 85px; padding: 15px; border-top: 1px solid var(--border-ui); }
.logout-panel { width: 100%; height: 50px; display: flex; align-items: center; border-radius: 2px; background: repeating-linear-gradient(45deg, rgba(255, 0, 85, 0.03), rgba(255, 0, 85, 0.03) 10px, rgba(255, 0, 85, 0.08) 10px, rgba(255, 0, 85, 0.08) 20px); border: 1px solid rgba(255, 0, 85, 0.3); color: #ff0055; cursor: pointer; transition: 0.3s; overflow: hidden; position: relative; }
.logout-panel:hover { background: #ff0055; color: #000; box-shadow: 0 0 20px rgba(255, 0, 85, 0.4); }
.plasma-wiper { position: absolute; top: 0; left: -100%; width: 50%; height: 100%; background: linear-gradient(90deg, transparent, rgba(255,255,255,0.6), transparent); transform: skewX(-20deg); transition: 0.5s; }
.logout-panel:hover .plasma-wiper { left: 150%; }
.canvas-header { height: 75px; display: flex; align-items: center; justify-content: space-between; padding: 0 40px; background: var(--side-bg); border-bottom: 1px solid var(--border-ui); }
.breadcrumb-spine { font-family: 'Share Tech Mono'; font-size: 0.9rem; }
.breadcrumb-spine .sep { color: var(--accent); margin: 0 12px; }
.canvas-footer { height: 45px; padding: 0 40px; display: flex; align-items: center; justify-content: space-between; }
.footer-line { flex: 1; height: 1px; background: linear-gradient(90deg, var(--border-ui), transparent); }
.footer-hard-info { margin-left: 20px; display: flex; gap: 25px; font-family: 'Share Tech Mono'; font-size: 0.7rem; color: var(--text-dim); }
.info-block { display: flex; gap: 8px; align-items: center; }
.info-block .label { opacity: 0.6; }
.info-block .val { color: var(--text-main); font-weight: bold; }
.info-block .val-ok { color: #00ff88; font-weight: bold; }
.info-block .val-lock { color: var(--accent); }
.photon-flash { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: #fff; opacity: 0; z-index: 9999; pointer-events: none; }
.photon-flash.trigger { opacity: 0.15; transition: 0.1s; }
.bezel-bracket { position: absolute; width: 15px; height: 15px; border: 2px solid var(--accent); opacity: 0.3; }
.tl { top: -1px; left: -1px; border-right: none; border-bottom: none; }
.tr { top: -1px; right: -1px; border-left: none; border-bottom: none; }
.bl { bottom: -1px; left: -1px; border-right: none; border-top: none; }
.br { bottom: -1px; right: -1px; border-left: none; border-top: none; }
@keyframes spin { from { transform: rotate(0deg); } to { transform: rotate(360deg); } }
@keyframes scan-v { 0%, 100% { top: 10%; opacity: 0; } 50% { top: 90%; opacity: 1; } }
.meta-glide-enter-active, .meta-glide-leave-active { transition: all 0.4s cubic-bezier(0.23, 1, 0.32, 1); }
.meta-glide-enter-from, .meta-glide-leave-to { opacity: 0; transform: translateX(-20px); }
</style>