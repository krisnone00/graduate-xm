<template>
  <div class="ultimate-sync-container">

    <div class="void-bg"></div>
    <div class="static-horizon-grid"></div>
    <div class="laser-alignment-bg"></div>
    <div class="resonance-aura"></div>
    <div class="vignette-heavy"></div>

    <div class="terminal-core" v-if="!data.isTransitioning">

      <div class="terminal-header">
        <div class="title-guide"><div class="guide-dot"></div><div class="guide-line"></div></div>
        <div class="title-main-row">
          <h1 class="chrome-solid">STORE</h1>
          <h1 class="wireframe-hollow">SYSTEM</h1>
        </div>
        <div class="status-indicator">
          <span class="pulse-dot"></span>
          <span class="status-msg">系统就绪 / READY_FOR_LINK</span>
        </div>
      </div>

      <div class="form-structure">

        <div class="module-group">
          <div class="module-meta"><span class="mod-id">ID_01</span> 身份标识 / IDENTITY</div>
          <div class="precision-socket" :class="{ 'focused': focusedField === 'username', 'error': fieldErrors.username }">
            <div class="socket-corner tl"></div><div class="socket-corner tr"></div>
            <div class="socket-corner bl"></div><div class="socket-corner br"></div>
            <div class="side-leds">
              <div class="led-dot" v-for="i in 3" :key="i" :class="{ 'blink': isTyping }"></div>
            </div>
            <input v-model="data.form.username" type="text" @focus="handleFocus('username')" @blur="handleBlur('username')" @input="onTyping('username')" placeholder=" " autocomplete="off">
            <span class="ghost-tip" v-if="focusedField === 'username' && !data.form.username">请输入访问账户...</span>
            <div class="right-gauge"><div class="gauge-bar" :style="{ height: data.form.username.length * 10 + '%' }"></div></div>
            <div class="socket-energy-track"><div class="energy-fill" :style="{ width: data.form.username.length * 10 + '%' }"></div></div>

            <transition name="err-tag">
              <div class="side-error-tag" v-if="fieldErrors.username">
                <span class="tag-arrow">>></span> 警告: 账号不能为空
              </div>
            </transition>
          </div>
        </div>

        <div class="module-group">
          <div class="module-meta"><span class="mod-id">ID_02</span> 访问密钥 / SECURE_KEY</div>
          <div class="precision-socket" :class="{ 'focused': focusedField === 'password', 'error': fieldErrors.password }">
            <div class="socket-corner tl"></div><div class="socket-corner tr"></div>
            <div class="socket-corner bl"></div><div class="socket-corner br"></div>
            <div class="side-leds"><div class="led-dot" v-for="i in 3" :key="i" :class="{ 'blink': isTyping }"></div></div>
            <input v-model="data.form.password" type="password" @focus="handleFocus('password')" @blur="handleBlur('password')" @input="onTyping('password')" placeholder=" ">
            <span class="ghost-tip" v-if="focusedField === 'password' && !data.form.password">请输入加密密钥...</span>
            <div class="right-gauge"><div class="gauge-bar" :style="{ height: data.form.password.length * 10 + '%' }"></div></div>
            <div class="socket-energy-track"><div class="energy-fill" :style="{ width: data.form.password.length * 10 + '%' }"></div></div>

            <transition name="err-tag">
              <div class="side-error-tag" v-if="fieldErrors.password">
                <span class="tag-arrow">>></span> 警告: 密钥缺失
              </div>
            </transition>
          </div>
        </div>

        <div class="module-group">
          <div class="module-meta"><span class="mod-id">ID_03</span> 权限协议 / CLEARANCE</div>
          <div class="module-select" :class="{ 'open': showDropdown }" @click="toggleDropdown">
            <span class="curr-val">{{ roleMap[data.form.role] }}</span>
            <div class="select-icon"></div>
            <transition name="unfold">
              <div v-if="showDropdown" class="module-dropdown">
                <div class="m-row" @click.stop="selectRole(1)">[01] 系统管理员</div>
                <div class="m-row" @click.stop="selectRole(2)">[02] 仓库主管</div>
                <div class="m-row" @click.stop="selectRole(3)">[03] 普通员工</div>
                <div class="m-row" @click.stop="selectRole(0)">[00] 访客权限</div>
              </div>
            </transition>
          </div>
        </div>

        <button class="core-btn" @click="login">
          <div class="btn-plasma-wave"></div>
          <div class="btn-laser-sweep"></div>
          <span class="btn-txt">建立系统连接</span>
        </button>

        <div class="tactical-register-link" @click="router.push('/register')">
          <div class="link-line"></div>
          <span class="link-label">申请新节点访问权限 / REGISTER</span>
          <div class="link-bracket"></div>
        </div>

      </div>
    </div>

    <transition name="welcome-fade">
      <div class="welcome-overlay" v-if="data.isTransitioning">
        <div class="welcome-content">
          <h1 class="access-text" :class="{ 'linked': data.syncPercent >= 100 }">
            {{ data.syncPercent >= 100 ? '连接已建立' : '访问授权成功' }}
          </h1>

          <div class="heavy-sync-loader">
            <div class="loader-info">神经链路同步中 / NEURAL_SYNCING... {{ data.syncPercent }}%</div>
            <div class="loader-track">
              <div class="loader-fill" :class="{ 'full': data.syncPercent >= 100 }" :style="{ width: data.syncPercent + '%' }"></div>
            </div>
          </div>

          <div class="bio-info-panel">
            <div class="panel-row">>> 接入主体: <span class="white-txt">{{ data.form.username }}</span></div>
            <div class="panel-row">>> 验证结果: 生物识别通过</div>
            <div class="panel-row" v-if="data.syncPercent >= 100">>> 正在重定向至核心管理系统...</div>
          </div>
        </div>
        <div class="vertical-laser-scan"></div>
      </div>
    </transition>

  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import request from '@/utils/request.js'
import { ElMessage } from 'element-plus'
import router from "@/router/index.js"

const showDropdown = ref(false)
const focusedField = ref('')
const isTyping = ref(false)

// 🌟 映射汉化
const roleMap = {
  1: '系统管理员',
  2: '仓库主管',
  3: '普通员工',
  0: '访客权限'
}

const data = reactive({
  form: { username: '', password: '', role: 1 },
  isTransitioning: false,
  syncPercent: 0
})

const fieldErrors = reactive({ username: false, password: false })

const startWelcomeSequence = () => {
  data.isTransitioning = true
  const interval = setInterval(() => {
    if (data.syncPercent < 100) {
      data.syncPercent += Math.floor(Math.random() * 18) + 4
      if (data.syncPercent > 100) data.syncPercent = 100
    } else {
      clearInterval(interval)
      setTimeout(() => {
        router.push('/manager').catch(() => router.push('/'))
      }, 1800)
    }
  }, 100)
}

const login = () => {
  fieldErrors.username = !data.form.username
  fieldErrors.password = !data.form.password
  if(fieldErrors.username || fieldErrors.password) return

  request.post('login', data.form).then(res => {
    if (res.code === 200) {
      localStorage.setItem('bs-xm-user', JSON.stringify(res.data))
      startWelcomeSequence()
    } else {
      ElMessage.error(res.msg || 'ACCESS DENIED')
    }
  })
}

let tTimer;
const onTyping = (f) => {
  fieldErrors[f] = false; isTyping.value = true;
  clearTimeout(tTimer); tTimer = setTimeout(() => { isTyping.value = false }, 500);
}
const handleFocus = (f) => { focusedField.value = f; fieldErrors[f] = false; }
const handleBlur = (f) => { focusedField.value = ''; }
const toggleDropdown = () => showDropdown.value = !showDropdown.value
const selectRole = (r) => { data.form.role = r; showDropdown.value = false; }
</script>

<style scoped>
@import url('https://gs.jurieo.com/gemini/fonts-googleapis/css2?family=Audiowide&family=Rajdhani:wght@500;600;700&family=Share+Tech+Mono&display=swap');

/* 全局色彩共振 */
@keyframes global-hue-shift { 0% { filter: hue-rotate(0deg); } 100% { filter: hue-rotate(360deg); } }

.ultimate-sync-container {
  position: fixed; top: 0; left: 0; width: 100%; height: 100%;
  background: #000; display: flex; justify-content: center; align-items: center;
  font-family: 'Rajdhani', "Microsoft YaHei", sans-serif; color: #fff;
  animation: global-hue-shift 35s infinite linear;
}

/* 环境背景 */
.void-bg { position: absolute; width: 100%; height: 100%; background: #000; z-index: -1; }
.static-horizon-grid { position: absolute; bottom: -30vh; left: -50%; width: 200%; height: 80vh; background: linear-gradient(transparent 0%, rgba(255,255,255,0.02) 1%, transparent 2%), linear-gradient(90deg, transparent 0%, rgba(255,255,255,0.02) 1%, transparent 2%); background-size: 60px 60px; transform: perspective(1000px) rotateX(75deg); mask-image: radial-gradient(circle at center, black 20%, transparent 100%); opacity: 0.5; }
.laser-alignment-bg { position: absolute; width: 100%; height: 100%; background-image: linear-gradient(rgba(255, 255, 255, 0.01) 1px, transparent 1px); background-size: 100% 40px; }
.resonance-aura { position: absolute; width: 1000px; height: 1000px; background: radial-gradient(circle at center, rgba(0, 243, 255, 0.03), transparent 75%); animation: slow-breath 8s infinite alternate; }
@keyframes slow-breath { from { opacity: 0.3; transform: scale(0.95); } to { opacity: 0.7; transform: scale(1.05); } }
.vignette-heavy { position: absolute; width: 100%; height: 100%; background: radial-gradient(circle, transparent 40%, #000 130%); pointer-events: none; }

.terminal-core { position: relative; z-index: 20; width: 480px; padding: 40px; }
.terminal-header { text-align: center; margin-bottom: 50px; }
.title-main-row { display: flex; justify-content: center; align-items: baseline; gap: 12px; }
.chrome-solid { font-size: 4.8rem; margin: 0; line-height: 0.85; font-weight: 700; background: linear-gradient(135deg, #fff 0%, #888 50%, #fff 100%); -webkit-background-clip: text; -webkit-text-fill-color: transparent; filter: drop-shadow(0 0 15px rgba(255,255,255,0.2)); }
.wireframe-hollow { font-size: 4.8rem; margin: 0; line-height: 0.85; font-weight: 700; color: transparent; -webkit-text-stroke: 1px #00f3ff; text-shadow: 0 0 20px rgba(0, 243, 255, 0.4); }
.status-msg { font-size: 0.8rem; color: #666; font-weight: bold; letter-spacing: 2px; }

/* 输入模块 */
.module-group { margin-bottom: 35px; position: relative; }
.module-meta { display: flex; align-items: center; gap: 8px; margin-bottom: 10px; font-size: 0.75rem; color: #666; font-weight: bold; }
.mod-id { background: #fff; color: #000; padding: 1px 4px; font-size: 0.65rem; }
.precision-socket { position: relative; height: 50px; background: rgba(255, 255, 255, 0.02); border: 1px solid rgba(255, 255, 255, 0.05); transition: 0.3s; }
.socket-corner { position: absolute; width: 6px; height: 6px; border: 1px solid #444; transition: 0.3s; }
.tl { top: -1px; left: -1px; border-right: none; border-bottom: none; }
.tr { top: -1px; right: -1px; border-left: none; border-bottom: none; }
.bl { bottom: -1px; left: -1px; border-right: none; border-top: none; }
.br { bottom: -1px; right: -1px; border-left: none; border-top: none; }
.precision-socket.focused { border-color: rgba(0, 243, 255, 0.4); }
.precision-socket.focused .socket-corner { border-color: #00f3ff; width: 12px; height: 12px; box-shadow: 0 0 10px #00f3ff; }
.precision-socket.error { border-color: #ff0055; background: rgba(255, 0, 85, 0.05); }

.side-leds { position: absolute; left: 8px; top: 12px; display: flex; flex-direction: column; gap: 4px; }
.led-dot { width: 3px; height: 3px; background: #333; }
.led-dot.blink { background: #00f3ff; animation: blink 0.2s infinite; }
.right-gauge { position: absolute; right: 8px; bottom: 8px; width: 2px; height: 60%; background: rgba(255,255,255,0.05); }
.gauge-bar { position: absolute; bottom: 0; width: 100%; background: #00f3ff; transition: height 0.3s; }
.precision-socket input { width: 100%; height: 100%; background: transparent; border: none; outline: none; color: #fff; font-family: 'Rajdhani', sans-serif; font-size: 1.1rem; padding: 0 25px; }
.socket-energy-track { position: absolute; bottom: 0; left: 0; width: 100%; height: 2px; background: rgba(255,255,255,0.02); }
.energy-fill { height: 100%; background: #00f3ff; transition: width 0.4s; box-shadow: 0 0 10px #00f3ff; }
.ghost-tip { position: absolute; left: 25px; top: 50%; transform: translateY(-50%); color: rgba(255,255,255,0.15); pointer-events: none; font-size: 0.9rem; }

/* 🌟 侧边战术提示语 */
.side-error-tag {
  position: absolute; left: 102%; top: 50%; transform: translateY(-50%);
  white-space: nowrap; font-family: "Microsoft YaHei", sans-serif;
  font-size: 0.7rem; font-weight: bold; color: #ff0055;
  letter-spacing: 1px; animation: tag-slide 0.3s ease;
}
.tag-arrow { animation: blink 0.5s infinite; margin-right: 5px; }

/* 按钮 */
.core-btn { width: 100%; height: 60px; margin-top: 25px; background: transparent; border: 2px solid #fff; color: #fff; font-family: "Microsoft YaHei", sans-serif; font-size: 1.1rem; font-weight: bold; letter-spacing: 4px; cursor: pointer; position: relative; overflow: hidden; transition: 0.4s; }
.btn-plasma-wave { position: absolute; top: 50%; left: 50%; width: 0; height: 0; background: #ff00de; border-radius: 50%; transform: translate(-50%, -50%); transition: width 0.6s; z-index: 1; opacity: 0; }
.btn-laser-sweep { position: absolute; top: 0; left: -100%; width: 50%; height: 100%; background: linear-gradient(90deg, transparent, rgba(255,255,255,0.8), transparent); transform: skewX(-30deg); z-index: 2; }
.btn-txt { position: relative; z-index: 10; }
.core-btn:hover { border-color: #00f3ff; color: #000; box-shadow: 0 0 30px #00f3ff; }
.core-btn:hover .btn-plasma-wave { width: 160%; height: 500%; opacity: 1; border-radius: 0; }
.core-btn:hover .btn-laser-sweep { left: 200%; transition: 0.7s; }

/* 注册链接 */
.tactical-register-link { margin-top: 35px; display: flex; align-items: center; justify-content: center; gap: 15px; cursor: pointer; transition: 0.3s; opacity: 0.5; }
.link-line { height: 1px; width: 30px; background: #00f3ff; }
.link-label { font-family: "Microsoft YaHei", sans-serif; font-size: 0.75rem; color: #00f3ff; letter-spacing: 1px; }
.link-bracket { width: 8px; height: 12px; border: 1px solid #00f3ff; border-left: none; }
.tactical-register-link:hover { opacity: 1; transform: translateY(-2px); }

/* 欢迎过场 */
.welcome-overlay { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: #000; z-index: 9999; display: flex; justify-content: center; align-items: center; }
.access-text { font-family: "Microsoft YaHei", sans-serif; font-size: 4rem; color: #fff; letter-spacing: 10px; text-shadow: 0 0 20px #00f3ff; transition: 0.5s; font-weight: 900; }
.access-text.linked { color: #00ff0a; text-shadow: 0 0 40px #00ff0a; transform: scale(1.1); }
.heavy-sync-loader { margin: 60px 0; }
.loader-info { font-family: 'Share Tech Mono', "Microsoft YaHei"; color: #00f3ff; font-size: 0.9rem; margin-bottom: 12px; text-align: left; }
.loader-track { height: 6px; background: rgba(255, 255, 255, 0.05); border-radius: 3px; overflow: hidden; width: 500px; }
.loader-fill { height: 100%; background: #00f3ff; box-shadow: 0 0 20px #00f3ff; transition: width 0.15s ease; }
.loader-fill.full { background: #00ff0a; box-shadow: 0 0 35px #00ff0a; }

.bio-info-panel { border-left: 3px solid #ff00de; padding-left: 25px; text-align: left; margin-top: 50px; }
.panel-row { font-family: "Microsoft YaHei", sans-serif; color: rgba(255,255,255,0.4); font-size: 1.1rem; margin: 10px 0; }
.white-txt { color: #fff; text-shadow: 0 0 10px #fff; }
.vertical-laser-scan { position: absolute; top: -10%; left: 0; width: 100%; height: 5px; background: #00f3ff; box-shadow: 0 0 20px #00f3ff; animation: scan-v 2.5s infinite linear; }

/* 下拉框样式 */
.module-select { border: 1px solid rgba(255,255,255,0.1); height: 50px; padding: 0 20px; cursor: pointer; display: flex; justify-content: space-between; align-items: center; background: rgba(255,255,255,0.02); position: relative; }
.module-dropdown { position: absolute; top: 105%; left: 0; width: 100%; background: rgba(10,10,10,0.95); border: 1px solid #00f3ff; z-index: 100; }
.m-row { padding: 12px; color: #888; border-bottom: 1px solid #222; font-weight: bold; font-size: 0.9rem; font-family: "Microsoft YaHei"; }
.m-row:hover { background: rgba(0,243,255,0.1); color: #fff; }

@keyframes blink { 50% { opacity: 0.1; } }
@keyframes scan-v { 0% { top: -10%; } 100% { top: 110%; } }
@keyframes tag-slide { from { opacity: 0; transform: translate(-10px, -50%); } to { opacity: 1; transform: translate(0, -50%); } }
</style>