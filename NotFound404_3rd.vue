<template>
  <div class="full-page-link">
    <header class="top-header"></header>

    <!-- Dust particles -->
    <div class="particles-container">
      <div class="starsec"></div>
      <div class="starthird"></div>
      <div class="starfourth"></div>
      <div class="starfifth"></div>
    </div>

    <!-- Lamp -->
    <div class="lamp__wrap">
      <div class="lamp">
        <div class="cable"></div>
        <div class="cover"></div>
        <div class="in-cover">
          <div class="bulb"></div>
        </div>
        <div class="light"></div>
      </div>
    </div>

    <!-- Error Content -->
    <section class="error">
      <div class="error__content">
        <div class="error__message message">
          <h1 class="message__title">页面未找到</h1>
          <p class="message__text">
            页面找不到了，请重试
          </p>
        </div>
        <div class="error__nav e-nav">
          <button @click="goHome" class="e-nav__link">Go Home</button>
          <button @click="goBack" class="e-nav__link">Go Back</button>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';


const router = useRouter();

const goHome = () => {
  router.push('/');
};

const goBack = () => {
  router.back();
};
</script>

<style scoped>
/* 仅添加必要的内联样式 */
.full-page-link {
  display: block;
  width: 100%;
  height: 100%;
  text-decoration: none;
  color: inherit;
}

.error__nav {
  margin-top: 40px;
}

.e-nav__link {
  height: 45px;
  line-height: 45px;
  width: 170px;
  display: inline-block;
  vertical-align: top;
  margin: 0 15px;
  border: 1px solid #181828;
  color: #181828;
  text-decoration: none;
  font-family: 'Montserrat', sans-serif;
  text-transform: uppercase;
  font-size: 11px;
  letter-spacing: .1rem;
  position: relative;
  overflow: hidden;
  background: none;
  cursor: pointer;
}

.e-nav__link:before {
  content: '';
  height: 200px;
  background: #212121;
  position: absolute;
  top: 70px;
  right: 70px;
  width: 260px;
  transition: all .3s;
  transform: rotate(50deg);
}

.e-nav__link:after {
  transition: all .3s;
  z-index: 999;
  position: relative;
}

.e-nav__link:hover:before {
  top: -60px;
  right: -50px;
}

.e-nav__link:hover {
  color: #fff;
}

/* 添加响应式调整 */
@media (max-width: 750px) {
  .error__nav {
    display: flex;
    flex-direction: column;
    gap: 15px;
    align-items: center;
  }

  .e-nav__link {
    margin: 5px 0;
  }
}
</style>

<style>
/* 全局引入CSS文件 */
@import '@/assets/css/NotFound404_3rd.css';
</style>