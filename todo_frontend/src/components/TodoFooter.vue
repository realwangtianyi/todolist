<template>
  <div>
    <el-row style="margin-top: 20px; ">
      <!-- <el-link type="primary">全部事项</el-link>
      <el-link type="primary">只看未完成事项</el-link>
      <el-link type="primary">只看未完成事项</el-link> -->
      <!-- <el-radio-group v-model="size">
        <el-radio label="">all</el-radio>
        <el-radio label="medium">active</el-radio>
        <el-radio label="small">completed</el-radio>
      </el-radio-group> -->
      <el-button size="small" type="success" round @click="showTab(1)">all</el-button>
      <el-button size="small" type="success" round @click="showTab(2)">active</el-button>
      <el-button size="small" type="success" round @click="showTab(3)">completed</el-button>
      <el-button size="small" type="danger"  round @click="clearCompleted">clear completed</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'TodoFooter',
  methods: {
    showTab (tabId) {
      this.$emit('showTab', tabId)
    },
    clearCompleted () {
      this.$emit('clearCompleted')
    }
  }
}
</script>

<style>
</style>
