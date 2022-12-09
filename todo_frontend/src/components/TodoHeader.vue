<template>
  <div>
    <el-input
      placeholder="请输入新增代办事项"
      v-model="newEventName"
      clearable
      @keyup.enter.native="addNewEvent"
    >
      <el-button slot="append" icon="el-icon-plus" @click="addNewEvent"></el-button>
    </el-input>
  </div>
</template>

<script>
export default {
  name: 'TodoHeader',
  data () {
    return {
      newEventName: null
    }
  },
  methods: {
    addNewEvent () {
      if (!this.newEventName) {
        console.log('不能为空！')
        return
      }
      console.log(this.newEventName)
      const todoObj = { name: this.newEventName, done: false, deleted: false }
      this.newEventName = ''
      // this.addTodo(todoObj)
      this.$emit('addTodo', todoObj)
    }
  }
}
</script>

<style>
</style>
