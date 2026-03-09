<template>
  <!-- 主容器 -->
  <div class="animation-container">
    <!-- SVG动画容器 -->
    <div class="netflix-container">
      <!-- SVG元素，ref绑定到vue实例 -->
      <svg ref="svgRef" width="867" height="233" viewBox="0 0 867 233" fill="none" xmlns="http://www.w3.org/2000/svg">
        <!-- N字母的SVG路径 -->
        <g id="N">
          <!-- N字母左侧竖线基础部分 -->
          <path id="N1-base" class="base" d="M0 232.8L37 227.8V101.6L35.1 0H0V232.8Z" />
          <!-- N字母左侧竖线阴影部分 -->
          <path id="N1-shadow" d="M0 232.8L37 227.8V101.6L35.1 0H0V232.8Z" fill="url(#N1-shadowFill)" />
          <!-- N字母右侧竖线基础部分 -->
          <path id="N3-base" class="base" d="M82.6 0H119V218.4L82.6 222.3V0Z" />
          <!-- N字母右侧竖线阴影部分 -->
          <path id="N3-shadow" d="M82.6 0H119V218.4L82.6 222.3V0Z" fill="url(#N3-shadowFill)" />
          <!-- N字母斜线部分 -->
          <path id="N2" class="base" d="M79.2 222.7L119 218.4L35.1 0H0L79.2 222.7Z" />
          <!-- 渐变定义 -->
          <defs>
            <!-- N字母左侧阴影渐变 -->
            <linearGradient id="N1-shadowFill" x1="50%" y1="0%" x2="50%" y2="100%" gradientUnits="userSpaceOnUse">
              <stop class="shadow-start" offset="0%" />
              <stop class="shadow-end" offset="100%" />
            </linearGradient>
            <!-- N字母右侧阴影渐变 -->
            <linearGradient id="N3-shadowFill" x1="50%" y1="100%" x2="50%" y2="0%" gradientUnits="userSpaceOnUse">
              <stop class="shadow-start" offset="0%" />
              <stop class="shadow-end" offset="100%" />
            </linearGradient>
          </defs>
        </g>

        <!-- E字母的SVG路径 -->
        <g id="E">
          <!-- E字母上部横线基础部分 -->
          <path id="E1-base" class="base" d="M255.1 171.6V208.2L153.6 215.8V178.5L255.1 171.6Z" />
          <!-- E字母上部横线阴影部分 -->
          <path id="E1-shadow" d="M255.1 171.6V208.2L153.6 215.8V178.5L255.1 171.6Z" fill="url(#E1-shadowFill)" />
          <!-- E字母中间横线 -->
          <path id="E4" class="base" d="M239.9 85.2V121.2L153.6 121.5V85.6L239.9 85.2Z" />
          <!-- E字母竖线基础部分 -->
          <path id="E2-base" class="base" d="M190.1 213.1L153.6 215.8V0H190.1V213.1Z" />
          <!-- E字母竖线阴影部分 -->
          <path id="E2-shadow" d="M190.1 213.1L153.6 215.8V0H190.1V213.1Z" fill="url(#E2-shadowFill)" />
          <!-- E字母顶部横线 -->
          <path id="E3" class="base" d="M255 0H153.6V36.4H255V0Z" />
          <!-- 渐变定义 -->
          <defs>
            <!-- E字母上部横线阴影渐变（水平方向） -->
            <linearGradient id="E1-shadowFill" x1="0%" y1="50%" x2="100%" y2="50%" gradientUnits="objectBoundingBox">
              <stop class="shadow-start" offset="0%" />
              <stop class="shadow-end" offset="100%" />
            </linearGradient>
            <!-- E字母竖线阴影渐变（垂直方向，只到40%） -->
            <linearGradient id="E2-shadowFill" x1="50%" y1="0%" x2="50%" y2="40%" gradientUnits="userSpaceOnUse">
              <stop class="shadow-start" offset="0%" />
              <stop class="shadow-end" offset="100%" />
            </linearGradient>
          </defs>
        </g>

        <!-- T字母的SVG路径 -->
        <g id="T">
          <!-- T字母竖线基础部分 -->
          <path id="T2-base" class="base" d="M317 0V204.6L353.4 203.2V0H317Z" />
          <!-- T字母竖线阴影部分 -->
          <path id="T2-shadow" d="M317 0V204.6L353.4 203.2V0H317Z" fill="url(#T2-shadowFill)" />
          <!-- T字母横线 -->
          <path id="T1" class="base" d="M391.7 0H278.7V36.4H391.7V0Z" />
          <!-- 渐变定义 -->
          <defs>
            <!-- T字母竖线阴影渐变 -->
            <linearGradient id="T2-shadowFill" x1="50%" y1="0%" x2="50%" y2="100%" gradientUnits="userSpaceOnUse">
              <stop class="shadow-start" offset="0%" />
              <stop class="shadow-end" offset="100%" />
            </linearGradient>
          </defs>
        </g>

        <!-- F字母的SVG路径 -->
        <g id="F">
          <!-- F字母中间横线 -->
          <path id="F3" class="base" d="M501 84H415V120H501V84Z" />
          <!-- F字母竖线基础部分 -->
          <path id="F2-base" class="base" d="M414.8 0V202.2H451.8V0H414.8Z" />
          <!-- F字母竖线阴影部分 -->
          <path id="F2-shadow" d="M414.8 0V202.2H451.8V0H414.8Z" fill="url(#F2-shadowFill)" />
          <!-- F字母顶部横线 -->
          <path id="F1" class="base" d="M516.8 0H414.8V36.4H516.8V0Z" />
          <!-- 渐变定义 -->
          <defs>
            <!-- F字母竖线阴影渐变（只到40%） -->
            <linearGradient id="F2-shadowFill" x1="50%" y1="0%" x2="50%" y2="40%" gradientUnits="userSpaceOnUse">
              <stop class="shadow-start" offset="0%" />
              <stop class="shadow-end" offset="100%" />
            </linearGradient>
          </defs>
        </g>

        <!-- L字母的SVG路径 -->
        <g id="L">
          <!-- L字母竖线基础部分 -->
          <path id="L1-base" class="base" d="M540.5 0H576.9V205.9L540.5 204.1V0Z" />
          <!-- L字母竖线阴影部分 -->
          <path id="L1-shadow" d="M540.5 0H576.9V205.9L540.5 204.1V0Z" fill="url(#L1-shadowFill)" />
          <!-- L字母底部横线 -->
          <path id="L2" class="base" d="M540.5 167.5L639.5 173.1V209.1L540.5 204.1V167.5Z" />
          <!-- 渐变定义 -->
          <defs>
            <!-- L字母竖线阴影渐变（从下到上） -->
            <linearGradient id="L1-shadowFill" x1="50%" y1="100%" x2="50%" y2="0%" gradientUnits="userSpaceOnUse">
              <stop class="shadow-start" offset="0%" />
              <stop class="shadow-end" offset="100%" />
            </linearGradient>
          </defs>
        </g>

        <!-- I字母的SVG路径（单独路径，没有g分组） -->
        <path id="I" class="base" d="M669.2 0H705.7V214.3L669.2 211.2V0Z" />

        <!-- X字母的SVG路径 -->
        <g id="X">
          <!-- X字母右侧斜线基础部分 -->
          <path id="X2-base" class="base" d="M737 0L825.1 227.2L866.8 232.8L776.5 0H737Z" />
          <!-- X字母右侧斜线阴影部分（特殊渐变） -->
          <path id="X2-shadow" d="M737 0L825.1 227.2L866.8 232.8L776.5 0H737Z" fill="url(#X2-shadowFill)" />
          <!-- X字母左侧斜线 -->
          <path id="X1" class="base" d="M733.1 216.8L771.5 220.8L866.8 0H826.7L733.1 216.8Z" />
          <!-- 渐变定义 -->
          <defs>
            <!-- X字母特殊阴影渐变（三段式：透明-不透明-透明） -->
            <linearGradient id="X2-shadowFill" x1="0%" y1="10%" x2="80%" y2="100%" gradientUnits="objectBoundingBox">
              <stop class="shadow-end" offset="0%" />
              <stop class="shadow-start" offset="50%" />
              <stop class="shadow-end" offset="100%" />
            </linearGradient>
          </defs>
        </g>
      </svg>
    </div>

    <!-- 主题切换按钮容器 -->
    <!-- 使用v-for循环渲染三个主题按钮 -->
    <div class="themesContainer">
      <div
          v-for="(color, index) in colorThemes"
          :key="index"
          class="theme"
          :style="getThemeElementStyle(color)"
          @click="changeTheme(index)"
      ></div>
    </div>
  </div>
</template>

<script setup>
// Vue 3 Composition API 导入
// ref: 创建响应式数据引用
// onMounted: 组件挂载后的生命周期钩子
// onUnmounted: 组件卸载前的生命周期钩子
// watch: 监听响应式数据变化
// computed: 创建计算属性
// nextTick: 等待下一个DOM更新周期
import { ref, onMounted, onUnmounted, watch, computed, nextTick } from 'vue'

// GSAP动画库导入
// gsap: 主动画库
// MorphSVGPlugin: SVG形状变形插件
// CustomEase: 自定义缓动函数插件
import gsap from 'gsap'
import { MorphSVGPlugin } from 'gsap/MorphSVGPlugin'
import { CustomEase } from 'gsap/CustomEase'

// 注册GSAP插件（必须步骤）
gsap.registerPlugin(MorphSVGPlugin, CustomEase)

// SVG元素的引用，在模板中通过ref="svgRef"绑定
const svgRef = ref(null)

// 主时间线实例，存储动画时间线以便控制
let masterTimeline = null

// CSS变量配置对象（响应式）
const cssVars = ref({
  primaryColor: '#E50914',      // 主色调（Netflix红色）
  shadowColor: 'black',         // 阴影颜色
  shadowIntensity: 0.6,         // 阴影强度（0-1）
  uiControlsSize: '30px',       // UI控件（主题按钮）大小
  bodyBg: 'black'               // 页面背景色
})

// 计算属性：容器样式
// 将cssVars的值转换为CSS变量和内联样式
const containerStyle = computed(() => ({
  '--primary-colour': cssVars.value.primaryColor,
  '--shadow-colour': cssVars.value.shadowColor,
  '--shadow-intensity': cssVars.value.shadowIntensity.toString(),
  '--UIControlsSize': cssVars.value.uiControlsSize,
  '--body-bg': cssVars.value.bodyBg,
  'background-color': cssVars.value.bodyBg
}))

// 颜色主题配置数组
// 每个主题对象包含theme（主色）和bg（背景色）
const colorThemes = ref([
  { theme: '#E50914', bg: 'black' },      // 主题1：Netflix红 + 黑背景
  { theme: 'white', bg: '#E50914' },      // 主题2：白色 + Netflix红背景
  { theme: '#CFFFE9', bg: '#002E19' }     // 主题3：浅绿 + 深绿背景
])

// 方法1：获取主题按钮样式（使用135度线性渐变创建45度分割效果）
const getThemeElementStyle = (color) => {
  return {
    // 线性渐变：从左上到右下，50%处分割两种颜色
    background: `linear-gradient(135deg, ${color.theme} 0%, ${color.theme} 50%, ${color.bg} 50%, ${color.bg} 100%)`,
    width: cssVars.value.uiControlsSize,   // 宽度
    height: cssVars.value.uiControlsSize   // 高度
  }
}

// 更新文档根元素的CSS变量
const updateRootCSSVariables = () => {
  const root = document.documentElement  // 获取HTML根元素

  // 设置CSS自定义属性
  root.style.setProperty('--primary-colour', cssVars.value.primaryColor)
  root.style.setProperty('--shadow-colour', cssVars.value.shadowColor)
  root.style.setProperty('--shadow-intensity', cssVars.value.shadowIntensity.toString())
  root.style.setProperty('--UIControlsSize', cssVars.value.uiControlsSize)
  root.style.setProperty('--body-bg', cssVars.value.bodyBg)
}

// 监听cssVars变化，自动更新CSS变量
// deep: true表示深度监听对象内部属性的变化
watch(cssVars, updateRootCSSVariables, { deep: true })

// 切换主题函数
const changeTheme = (index) => {
  // 获取选中的主题
  const theme = colorThemes.value[index]

  // 更新CSS变量
  cssVars.value.primaryColor = theme.theme    // 主色
  cssVars.value.shadowColor = theme.bg        // 阴影色
  cssVars.value.bodyBg = theme.bg             // 背景色

  // 直接设置body背景色（确保立即生效）
  document.body.style.backgroundColor = theme.bg

  // 更新CSS变量
  updateRootCSSVariables()
}

// ========== 动画函数部分 ==========

// N字母动画
const createNAnimation = () => {
  const tl = gsap.timeline()  // 创建时间线
  tl
      .to("#N1-shadow", { opacity: 0, duration: 0.5 }, 0.3)  // N1阴影淡出
      .to("#N3-shadow", { opacity: 0, duration: 1.5 }, 0.3)  // N3阴影淡出
  return tl
}

// E字母动画（使用MorphSVGPlugin进行形状变形）
const createEAnimation = () => {
  // E1的原始形状（变形前的形状）
  const e_ogShape = "M255.1 171.6V208.2L250.5 208.5L250 172L255.1 171.6Z"
  const tl = gsap.timeline()
  tl
      .from("#E1-base", {
        morphSVG: { shape: e_ogShape, type: "linear" },  // 形状变形动画
        opacity: 0,      // 从透明开始
        duration: 0.15   // 动画时长0.15秒
      }, 0)  // 立即开始
      .from("#E2-base", {
        scaleY: 0,                 // Y轴缩放为0（完全压缩）
        transformOrigin: "50% 100%", // 变换原点在底部中心
        duration: 0.1
      }, 0.11)  // 延迟0.11秒开始
      .from("#E3", {
        scaleX: 0,       // X轴缩放为0
        duration: 0.06
      }, 0.21)  // 延迟0.21秒开始
      .from("#E4", {
        scaleX: 0,
        duration: 0.18
      }, 0.27)  // 延迟0.27秒开始
      .to("#E1-shadow, #E2-shadow", {
        opacity: 0,
        duration: 0.8
      }, 0)  // 立即开始
  return tl
}

// T字母动画
const createTAnimation = () => {
  const tl = gsap.timeline()
  tl
      .from("#T1", {
        scaleX: 0,
        duration: 0.1
      }, 0)
      .from("#T2-base", {
        scaleY: 0,
        duration: 0.25
      }, 0.1)
      .to("#T2-shadow", {
        opacity: 0,
        duration: 0.82
      })
  return tl
}

// F字母动画
const createFAnimation = () => {
  const tl = gsap.timeline()
  tl
      .from("#F1", {
        scaleX: 0,
        duration: 0.15
      }, 0)
      .from("#F2-base", {
        scaleY: 0,
        duration: 0.33
      }, 0.1)
      .from("#F3", {
        scaleX: 0,
        duration: 0.15
      }, 0.28)
      .to("#F2-shadow", {
        opacity: 0,
        duration: 0.86
      })
  return tl
}

// L字母动画（使用MorphSVGPlugin）
const createLAnimation = () => {
  // L2的原始形状
  const l_ogShape = "M540.5 167.5L546 167.781V204.371L540.5 204.1V167.5Z"
  const tl = gsap.timeline()
  tl
      .from("#L1-base", {
        scaleY: 0,
        duration: 0.22
      }, 0)
      .from("#L2", {
        morphSVG: { shape: l_ogShape, type: "linear" },
        opacity: 0,
        duration: 0.1
      }, 0.2)
      .to("#L1-shadow", {
        opacity: 0,
        duration: 0.83
      })
  return tl
}

// I字母动画
const createIAnimation = () => {
  const tl = gsap.timeline()
  tl
      .from("#I", {
        scaleY: 0,
        transformOrigin: "50% 100%",  // 变换原点在底部
        duration: 0.18
      }, 0)
  return tl
}

// X字母动画（使用MorphSVGPlugin，复杂形状变形）
const createXAnimation = () => {
  // X1和X2的原始形状
  const x1_ogShape = "M733.1 216.8L771.5 220.8L772.5 218.5L734.5 213.5L733.1 216.8Z"
  const x2_ogShape = "M737 0L738 2.5H777.5L776.5 0H737Z"
  const tl = gsap.timeline()
  tl
      .from("#X1", {
        morphSVG: { shape: x1_ogShape, type: "linear", shapeIndex: 2 },  // shapeIndex指定变形算法
        duration: 0.63
      }, 0)
      .from("#X1", {
        opacity: 0,
        duration: 0.1
      }, 0)
      .from("#X2-base", {
        morphSVG: { shape: x2_ogShape, type: "linear", shapeIndex: 2 },
        duration: 0.53
      }, 0.11)
      .from("#X2-base", {
        opacity: 0,
        duration: 0.01
      }, 0.11)
      .to("#X2-shadow", {
        opacity: 0,
        duration: 1.3
      }, 0)
  return tl
}

// 主移动动画（SVG整体进入动画）
const createMovementAnimation = () => {
  const tl = gsap.timeline()
  tl
      .from(svgRef.value, {
        opacity: 0,      // 从透明开始
        duration: 0.7    // 淡入时长0.7秒
      }, 0)
      .from(svgRef.value, {
        xPercent: 50,    // 水平移动50%
        left: "50%",     // 定位到50%
        duration: 1.9,   // 滑动时长1.9秒
        // 自定义缓动函数：创建特殊的缓动曲线
        ease: CustomEase.create("custom", "M0,0,C0.358,0.144,0.098,1,1,1")
      }, 0.7)  // 延迟0.7秒开始
  return tl
}

// 退出动画（SVG整体淡出）
const createExitAnimation = () => {
  const tl = gsap.timeline()
  tl
      .to(svgRef.value, {
        opacity: 0,
        duration: 0.5
      })
  return tl
}

// 创建主时间线（组合所有动画）
const createMasterTimeline = () => {
  // 清理旧的时间线（防止重复创建）
  if (masterTimeline) {
    masterTimeline.kill()
    masterTimeline = null
  }

  // 创建主时间线，设置无限循环和重复延迟
  masterTimeline = gsap.timeline({
    repeat: -1,       // -1表示无限循环
    repeatDelay: 1    // 每次循环间隔1秒
  })

  // 添加所有动画到主时间线，精确控制时间点
  masterTimeline
      .add(createMovementAnimation(), 0)    // 0秒：移动动画
      .add(createNAnimation(), 0.7)         // 0.7秒：N动画
      .add(createEAnimation(), 0.8)         // 0.8秒：E动画
      .add(createTAnimation(), 1.08)        // 1.08秒：T动画
      .add(createFAnimation(), 1.18)        // 1.18秒：F动画
      .add(createLAnimation(), 1.33)        // 1.33秒：L动画
      .add(createIAnimation(), 1.63)        // 1.63秒：I动画
      .add(createXAnimation(), 1.7)         // 1.7秒：X动画
      .add(createExitAnimation(), 6)        // 6秒：退出动画

  console.log('主时间线已创建，开始播放...')
}

// 手动重启动画（调试用）
const restartAnimation = () => {
  if (masterTimeline) {
    masterTimeline.restart()  // 重启时间线
  } else {
    createMasterTimeline()    // 创建新时间线
  }
}

// 初始化函数
const init = () => {
  console.log('初始化开始...')

  // 设置初始主题
  updateRootCSSVariables()
  document.body.style.backgroundColor = colorThemes.value[0].bg

  // 等待DOM完全渲染
  nextTick(() => {
    console.log('DOM 已渲染，开始创建动画...')

    // 检查SVG元素是否存在
    if (!svgRef.value) {
      console.error('SVG 元素未找到！')
      return
    }

    console.log('SVG 元素找到，开始创建时间线...')

    // 创建动画
    createMasterTimeline()

    // 调试信息
    setTimeout(() => {
      console.log('动画状态：', masterTimeline ? '已创建' : '未创建')
      if (masterTimeline) {
        console.log('时间线总时长：', masterTimeline.totalDuration())
        console.log('时间线是否活动：', masterTimeline.isActive())
      }
    }, 100)
  })
}

// 调试方法（在控制台查看动画状态）
const debugAnimation = () => {
  console.log('=== 动画调试信息 ===')
  console.log('SVG ref:', svgRef.value)
  console.log('主时间线:', masterTimeline)
  if (masterTimeline) {
    console.log('时间线状态:', {
      totalDuration: masterTimeline.totalDuration(),  // 总时长
      isActive: masterTimeline.isActive(),           // 是否活动
      progress: masterTimeline.progress(),           // 当前进度
      time: masterTimeline.time()                    // 当前时间
    })
  }

  // 尝试手动播放
  restartAnimation()
}

// Vue生命周期钩子：组件挂载后执行
onMounted(() => {
  console.log('组件挂载完成')
  init()  // 初始化动画

  // 暴露调试方法到全局（方便在浏览器控制台调试）
  window.debugAnimation = debugAnimation
  window.restartAnimation = restartAnimation
})

// Vue生命周期钩子：组件卸载前执行
onUnmounted(() => {
  // 清理动画（防止内存泄漏）
  if (masterTimeline) {
    masterTimeline.kill()
    masterTimeline = null
  }

  // 清理全局方法
  delete window.debugAnimation
  delete window.restartAnimation
})
</script>

<style scoped>
/* 基础SVG路径样式 */
.base {
  fill: var(--primary-colour, #E50914);  /* 使用CSS变量设置填充色，备用值为#E50914 */
  transition: fill 0.4s;  /* 填充色变化过渡效果，时长0.4秒 */
}

/* 阴影渐变起始点样式 */
.shadow-start {
  stop-color: var(--shadow-colour, black);          /* 使用CSS变量设置阴影颜色 */
  stop-opacity: var(--shadow-intensity, 0.6);       /* 使用CSS变量设置阴影透明度 */
}

/* 阴影渐变结束点样式 */
.shadow-end {
  stop-color: var(--shadow-colour, black);          /* 阴影颜色 */
  stop-opacity: 0;  /* 完全透明 */
}

/* 页面基础容器样式 */
.animation-container {
  height: 100vh;  /* 高度占满整个视口 */
  display: flex;
  flex-direction: column;  /* 垂直排列子元素 */
  justify-content: center; /* 垂直居中 */
  align-items: center;     /* 水平居中 */
  background-color: var(--body-bg, black);  /* 使用CSS变量设置背景色 */
  transition: background-color 0.4s;  /* 背景色变化过渡效果 */
}

/* Netflix SVG容器样式 */
.netflix-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh;  /* 高度占80%视口 */
  width: 100%;
}

/* SVG元素样式 */
svg {
  margin: 15vmin;  /* 使用视口最小单位作为边距，响应式设计 */
  max-width: 90vw; /* 最大宽度为90%视口宽度 */
}

/* 主题控件容器样式 */
.themesContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;   /* 上边距20px */
  padding: 20px;      /* 内边距20px */
  flex-wrap: wrap;    /* 允许子元素换行 */
}

/* 主题按钮方案1样式（直接渐变背景） */
.theme-option1 {
  margin: 0 20px;      /* 左右边距20px */
  border-radius: 50%;  /* 圆形边框半径 */
  border: 3px solid white;  /* 白色边框，宽度3px */
  transition: transform 0.2s;  /* 变换过渡效果，时长0.2秒 */
  cursor: pointer;  /* 鼠标指针变为手型 */
}

/* 主题按钮方案1悬停效果 */
.theme-option1:hover {
  transform: scale(1.1);  /* 悬停时放大10% */
}

/* 主题按钮通用样式 */
.theme {
  margin: 0 20px;      /* 左右边距20px */
  border-radius: 50%;  /* 圆形 */
  border: 3px solid white;  /* 白色边框 */
  transition: transform 0.2s;  /* 变换过渡 */
  cursor: pointer;  /* 手型光标 */
}

/* 主题按钮悬停效果 */
.theme:hover {
  transform: scale(1.1);  /* 悬停时放大10% */
}

/* 主题容器样式 */
.themesContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

</style>

<style>
/* 全局样式（非scoped，影响整个页面） */

/* CSS自定义属性（变量）定义在根元素上 */
:root {
  --primary-colour: #E50914;    /* 主色调：Netflix红色 */
  --shadow-colour: black;       /* 阴影颜色：黑色 */
  --shadow-intensity: 0.6;      /* 阴影强度：60%不透明度 */
  --UIControlsSize: 30px;       /* UI控件大小：30像素 */
  --body-bg: black;             /* 页面背景色：黑色 */
}

/* 页面body元素样式 */
body {
  height: 100vh;      /* 高度占满整个视口 */
  margin: 0;          /* 清除默认外边距 */
  padding: 0;         /* 清除默认内边距 */
  overflow: hidden;   /* 隐藏溢出内容 */
  background-color: var(--body-bg, black);  /* 使用CSS变量设置背景色 */
  transition: background-color 0.4s;  /* 背景色变化过渡效果 */
}
</style>