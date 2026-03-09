<template>
  <div class="workspace-deck">
    <div class="data-holodeck">
      <div class="holo-card primary">
        <div class="card-deco tl"></div><div class="card-deco br"></div>
        <div class="card-icon">⌬</div>
        <div class="card-info">
          <div class="label">TODAY_SALES // 今日销售</div>
          <div class="value">¥ <span class="glitch-num">24,592</span>.00</div>
          <div class="trend up">▲ 12.5% SYNC</div>
        </div>
        <div class="bg-scan"></div>
      </div>

      <div class="holo-card danger">
        <div class="card-deco tl"></div><div class="card-deco br"></div>
        <div class="card-icon">◬</div>
        <div class="card-info">
          <div class="label">STOCK_ALERT // 库存预警</div>
          <div class="value"><span class="blink-red">04</span> ITEMS</div>
          <div class="trend down">REQUIRES ATTENTION</div>
        </div>
        <div class="bg-pulse"></div>
      </div>

      <div class="holo-card info">
        <div class="card-deco tl"></div><div class="card-deco br"></div>
        <div class="card-icon">⧇</div>
        <div class="card-info">
          <div class="label">ACTIVE_NODES // 在线终端</div>
          <div class="value">18 / 20</div>
          <div class="trend stable">● STABLE</div>
        </div>
      </div>

      <div class="holo-card warning">
        <div class="card-deco tl"></div><div class="card-deco br"></div>
        <div class="card-icon">▤</div>
        <div class="card-info">
          <div class="label">PENDING_ORDERS // 待处理单</div>
          <div class="value">07 TASKS</div>
          <div class="trend flat">-- QUEUED</div>
        </div>
      </div>
    </div>

    <div class="main-tactical-grid">

      <div class="quick-command-zone panel-frame">
        <div class="panel-header">
          <span class="p-title">QUICK_ACCESS // 快速链路</span>
          <div class="p-line"></div>
        </div>
        <div class="command-grid">
          <div class="cmd-btn" @click="$router.push('/manager/goods')">
            <div class="cmd-icon">✚</div>
            <div class="cmd-label">入库登记</div>
            <div class="cmd-hover-fill"></div>
          </div>
          <div class="cmd-btn">
            <div class="cmd-icon">⚿</div>
            <div class="cmd-label">收银台</div>
            <div class="cmd-hover-fill"></div>
          </div>
          <div class="cmd-btn">
            <div class="cmd-icon">◈</div>
            <div class="cmd-label">盘点模式</div>
            <div class="cmd-hover-fill"></div>
          </div>
          <div class="cmd-btn">
            <div class="cmd-icon">☫</div>
            <div class="cmd-label">员工排班</div>
            <div class="cmd-hover-fill"></div>
          </div>
        </div>
      </div>

      <div class="core-chart-view panel-frame">
        <div class="panel-header">
          <span class="p-title">SALES_ANALYTICS // 销售趋势</span>
          <div class="p-tags">
            <span class="tag active">REALTIME</span>
            <span class="tag">WEEKLY</span>
          </div>
        </div>
        <div class="chart-placeholder">
          <div class="grid-lines"></div>
          <div class="simulated-graph"></div>
          <div class="scanning-beam"></div>
          <div class="center-text">DATA_VISUALIZATION_MODULE_LOADING...</div>
        </div>
      </div>

      <div class="system-log-stream panel-frame">
        <div class="panel-header">
          <span class="p-title">SYSTEM_LOG // 终端日志</span>
          <div class="status-led blink"></div>
        </div>
        <div class="log-container">
          <div class="log-item" v-for="(log, i) in logs" :key="i">
            <span class="log-time">[{{ log.time }}]</span>
            <span class="log-type" :class="log.type">{{ log.type }}</span>
            <span class="log-msg">{{ log.msg }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const logs = ref([
  { time: '10:42:01', type: 'INFO', msg: 'System initialized successfully.' },
  { time: '10:42:05', type: 'AUTH', msg: 'User [ADMIN] connected via Node_01.' },
  { time: '10:43:12', type: 'WARN', msg: 'Inventory sync delay (24ms).' },
  { time: '10:45:00', type: 'SALE', msg: 'Trans ID #99283 completed.' },
  { time: '10:48:33', type: 'INFO', msg: 'Database backup sequence started.' },
])

// 模拟日志滚动
onMounted(() => {
  setInterval(() => {
    const newLog = {
      time: new Date().toLocaleTimeString('en-GB'),
      type: Math.random() > 0.8 ? 'WARN' : 'INFO',
      msg: `Heartbeat sync: Packet_${Math.floor(Math.random()*9999)} received.`
    }
    logs.value.unshift(newLog)
    if (logs.value.length > 8) logs.value.pop()
  }, 3000)
})
</script>

<style scoped>
/* 继承 Manager 的变量 (假设 Manager 已经定义了 CSS 变量)
   如果单独显示不正常，请手动定义以下变量 */
.workspace-deck {
  height: 100%;
  display: flex;
  flex-direction: column;
  gap: 20px;
  color: var(--text-main, #fff);
  font-family: 'Share Tech Mono', sans-serif;
}

/* === 1. 全息磁贴 === */
.data-holodeck {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
  height: 120px;
}

.holo-card {
  position: relative;
  background: rgba(255,255,255,0.02);
  border: 1px solid rgba(255,255,255,0.1);
  padding: 20px;
  display: flex;
  align-items: center;
  gap: 15px;
  overflow: hidden;
  transition: 0.3s;
}

.holo-card:hover {
  background: rgba(255,255,255,0.05);
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(0,0,0,0.3);
}

/* 装饰角标 */
.card-deco { position: absolute; width: 8px; height: 8px; border: 1px solid transparent; transition: 0.3s; }
.holo-card:hover .card-deco { width: 12px; height: 12px; }
.tl { top: 0; left: 0; border-top-color: var(--accent, #00f3ff); border-left-color: var(--accent, #00f3ff); }
.br { bottom: 0; right: 0; border-bottom-color: var(--accent, #00f3ff); border-right-color: var(--accent, #00f3ff); }

.card-icon {
  font-size: 2.5rem;
  color: rgba(255,255,255,0.2);
}

.card-info { flex: 1; z-index: 2; }
.label { font-size: 0.7rem; color: rgba(255,255,255,0.5); letter-spacing: 1px; margin-bottom: 5px; }
.value { font-size: 1.8rem; font-weight: bold; font-family: 'Rajdhani'; line-height: 1; }
.trend { font-size: 0.7rem; margin-top: 5px; font-weight: bold; }

/* 颜色变体 */
.primary .value, .primary .trend { color: #00f3ff; }
.danger .value, .danger .trend { color: #ff0055; }
.info .value, .info .trend { color: #00ff88; }
.warning .value, .warning .trend { color: #ffcc00; }

.blink-red { animation: blink 1s infinite; }

/* 背景特效 */
.bg-scan {
  position: absolute; top: 0; left: -100%; width: 50%; height: 100%;
  background: linear-gradient(90deg, transparent, rgba(0, 243, 255, 0.1), transparent);
  transform: skewX(-20deg);
  animation: scan-once 5s infinite;
}

/* === 2. 中部战术网格 === */
.main-tactical-grid {
  flex: 1;
  display: grid;
  grid-template-columns: 250px 1fr 300px;
  gap: 20px;
  min-height: 0; /* 防止溢出 */
}

.panel-frame {
  border: 1px solid rgba(255,255,255,0.1);
  background: rgba(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  position: relative;
}

.panel-header {
  height: 40px;
  border-bottom: 1px solid rgba(255,255,255,0.1);
  display: flex;
  align-items: center;
  padding: 0 15px;
  justify-content: space-between;
  background: rgba(255,255,255,0.02);
}

.p-title { font-size: 0.8rem; letter-spacing: 1px; color: rgba(255,255,255,0.7); font-weight: bold; }

/* 快捷指令区 */
.command-grid {
  padding: 20px;

  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 15px;
}

.cmd-btn {
  aspect-ratio: 1;
  border: 1px dashed rgba(255,255,255,0.2);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  position: relative;
  transition: 0.3s;
  overflow: hidden;
}

.cmd-btn:hover { border-color: #00f3ff; color: #000; }
.cmd-icon { font-size: 1.5rem; margin-bottom: 10px; z-index: 2; }
.cmd-label { font-size: 0.8rem; z-index: 2; }
.cmd-hover-fill {
  position: absolute; top: 100%; left: 0; width: 100%; height: 100%;
  background: #00f3ff; transition: 0.3s; z-index: 1;
}
.cmd-btn:hover .cmd-hover-fill { top: 0; }

/* 模拟图表区 */
.core-chart-view { position: relative; overflow: hidden; }
.chart-placeholder {
  flex: 1;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  color: rgba(255,255,255,0.3);
}
.grid-lines {
  position: absolute; width: 100%; height: 100%;
  background-image: linear-gradient(rgba(255,255,255,0.05) 1px, transparent 1px),
  linear-gradient(90deg, rgba(255,255,255,0.05) 1px, transparent 1px);
  background-size: 20px 20px;
}
.simulated-graph {
  width: 80%; height: 60%;
  border-left: 2px solid #00f3ff;
  border-bottom: 2px solid #00f3ff;
  background: linear-gradient(to top, rgba(0,243,255,0.1), transparent);
  clip-path: polygon(0 100%, 10% 80%, 20% 85%, 30% 60%, 40% 70%, 50% 40%, 60% 50%, 70% 20%, 80% 30%, 90% 10%, 100% 0, 100% 100%);
  position: relative;
  z-index: 2;
}
.scanning-beam {
  position: absolute; left: 0; top: 0; height: 100%; width: 2px;
  background: #00f3ff;
  box-shadow: 0 0 20px #00f3ff;
  animation: scan-chart 3s infinite linear;
  z-index: 3;
}

/* 日志流 */
.log-container {
  padding: 15px;
  font-family: 'Courier New', monospace;
  font-size: 0.75rem;
  display: flex;
  flex-direction: column;
  gap: 8px;
  overflow: hidden;
}
.log-item { display: flex; gap: 10px; opacity: 0.8; }
.log-time { color: #666; }
.log-type.INFO { color: #00ff88; }
.log-type.WARN { color: #ffcc00; }
.log-type.AUTH { color: #00f3ff; }
.log-type.SALE { color: #ff00de; }

/* 动画 */
@keyframes scan-once { 0% { left: -100%; } 100% { left: 200%; } }
@keyframes blink { 50% { opacity: 0.2; } }
@keyframes scan-chart { 0% { left: 0; } 100% { left: 100%; } }
</style>