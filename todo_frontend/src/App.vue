<template>
  <div id="app">
    <TodoIndex></TodoIndex>
  </div>
</template>

<script>
import TodoIndex from './components/TodoIndex'

export default {
  name: 'app',
  components: { TodoIndex }
}
</script>

<style>
#app{
  background-color: wheat;
  height: auto;
}
</style>
