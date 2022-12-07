<template>
  <div id="app">
    <!-- 路由占位符 -->
    <router-view></router-view>
  </div>
</template>

<script>
import Index from './components/Index'

export default {
  name: 'app',
  components: { Index }
}
</script>

<style>

</style>
