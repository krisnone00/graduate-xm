<template>
  <div class="transition-screen">
    <link href="https://gs.jurieo.com/gemini/fonts-googleapis/css2?family=Bebas+Neue&display=swap" rel="stylesheet">

    <div class="glitch-wrapper">
      <svg class="svg-layer svg-shadow" viewBox="0 0 800 150">
        <text
            x="50%" y="50%"
            dominant-baseline="middle" text-anchor="middle"
            class="glitch-text shadow-text"
        >
          WELCOME
        </text>
      </svg>

      <svg class="svg-layer svg-front" viewBox="0 0 800 150">
        <defs>
          <linearGradient id="cyber-pink-grad" x1="0%" y1="0%" x2="0%" y2="100%">
            <stop offset="0%" stop-color="#FF00CC" />
            <stop offset="100%" stop-color="#333399" />
          </linearGradient>
        </defs>

        <text
            x="50%" y="50%"
            dominant-baseline="middle" text-anchor="middle"
            class="glitch-text front-text"
        >
          WELCOME
        </text>
      </svg>

      <div class="scanlines"></div>
    </div>
  </div>
</template>

<script setup>
// 这里不需要额外的 JS逻辑，纯 CSS 动画即可完成过渡效果。
// 你可以在父组件中控制这个组件的显示时长，例如显示 4秒 后切换到主页。
</script>

<style scoped>
.transition-screen {
  width: 100%;
  height: 100vh;
  background-color: #050505; /* 纯黑背景起始 */
  background-image: radial-gradient(circle, #1a0a1f 0%, #000000 100%); /* 微妙的紫色光晕 */
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  overflow: hidden;
  font-family: 'Bebas Neue', sans-serif;
  /* 整个屏幕也加一个缓慢的淡入，避免切换时太生硬 */
  animation: screen-fade-in 1s ease-out forwards;
}

.glitch-wrapper {
  position: relative;
  width: 90%;
  max-width: 900px;
  height: 200px;
}

.svg-layer {
  position: absolute;
  top: 0; left: 0;
  width: 100%; height: 100%;
  overflow: visible;
}

.glitch-text {
  font-size: 150px;
  letter-spacing: 10px;
  font-weight: bold;
}

/* =========================================
   关键修改点：精心设计的渐入动画序列
========================================= */

/* --- 1. 扫描线 (最先出现) --- */
.scanlines {
  position: absolute;
  top: 0; left: 0;
  width: 100%; height: 100%;
  background: linear-gradient(to bottom, transparent 50%, rgba(0,0,0,0.4) 50%);
  background-size: 100% 4px;
  z-index: 20;
  pointer-events: none;
  opacity: 0; /* 初始隐藏 */
  /* 0s 开始，用 1.5s 慢慢浮现 */
  animation: scanline-fade-in 1.5s ease-out forwards 0s;
}

/* --- 2. 阴影故障层 (接着出现) --- */
.shadow-text {
  fill: #240024;
  stroke: #ff0055;
  stroke-width: 2px;
  opacity: 0; /* 初始隐藏 */

  /* 组合动画：
     1. shadow-appear: 延时 0.5s 后，用 2s 时间慢慢淡入到 0.6 透明度
     2. shadow-pulse: 持续不断的故障抖动
  */
  animation:
      shadow-appear 2s ease-out forwards 0.5s,
      shadow-pulse 0.2s infinite alternate; /* 加快抖动频率，更有故障感 */
}

/* --- 3. 前景主体层 (最后登场) --- */
.front-text {
  fill: url(#cyber-pink-grad);
  filter: drop-shadow(0 0 8px rgba(255, 0, 204, 0.6));
  opacity: 0; /* 初始隐藏 */

  /* 描边动画准备 */
  stroke: #00ffff; /* 先用青色描边，对比度更高 */
  stroke-width: 3px;
  stroke-dasharray: 1000;
  stroke-dashoffset: 1000;

  /* 组合动画：
     重要：delay 设置为 2s，确保背景层都出来了，它才开始画。
  */
  animation:
      cyber-draw-in 2.5s cubic-bezier(0.19, 1, 0.22, 1) forwards 2s,
      float-main 3s ease-in-out infinite alternate 2s;
}


/* ==================== 动画关键帧定义 ==================== */

/* 屏幕整体淡入 */
@keyframes screen-fade-in {
  from { opacity: 0; } to { opacity: 1; }
}

/* 扫描线淡入 */
@keyframes scanline-fade-in {
  to { opacity: 0.5; }
}

/* 阴影层淡入 (只运行一次) */
@keyframes shadow-appear {
  to { opacity: 0.6; }
}

/* 阴影层故障抖动 (无限循环) */
@keyframes shadow-pulse {
  0% { transform: translate(2px, 0); }
  100% { transform: translate(-2px, 0); }
}

/* 主体描边进入动画 (重写以配合渐入) */
@keyframes cyber-draw-in {
  0% {
    opacity: 1; /* 开始绘制时设为可见 */
    stroke-dashoffset: 1000;
    fill-opacity: 0;
    transform: scale(0.9); /* 稍微缩小开始 */
  }
  60% {
    stroke-dashoffset: 0; /* 描边完成 */
    fill-opacity: 0;
    stroke: #FF00CC; /* 描边变粉色 */
  }
  100% {
    opacity: 1;
    stroke-dashoffset: 0;
    fill-opacity: 1; /* 填充完成 */
    stroke: transparent; /* 隐藏描边 */
    transform: scale(1); /* 回到正常大小 */
  }
}

/* 主体悬浮 */
@keyframes float-main {
  from { transform: translateY(0); }
  to { transform: translateY(-8px); }
}
</style>