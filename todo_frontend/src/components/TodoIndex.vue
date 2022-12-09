<template>
  <div class="todo_container">
    <div class="todo_box" style="height: auto">
      <!-- 头像区 -->
      <div class="avatar_box">
        <img src="../assets/logo3.png" />
      </div>
      <!-- todo主体区 -->
      <div class="todo_form" style="height: auto">
        <todo-header @addTodo="addTodo"></todo-header>
        <todo-list
          :eventList="filteredList"
          @remove="remove"
          @update="update"
          style="margin-top: 20px"
        ></todo-list>
        <todo-footer @showTab="showTab" @clearCompleted="clearCompleted"></todo-footer>
      </div>
    </div>
  </div>
</template>

<script>
import TodoHeader from './TodoHeader.vue'
import TodoList from './TodoList.vue'
import TodoFooter from './TodoFooter.vue'
export default {
  name: 'TodoIndex',
  components: { TodoHeader, TodoList, TodoFooter },
  data () {
    return {
      flagAll: true,
      flagActive: false,
      flagCompleted: false,
      eventList: [],
      filteredList: []
      // eventList: [
      //   { id: '001', name: '去王克桢楼', done: true },
      //   { id: '002', name: '去吃饭', done: true }
      // ]
    }
  },
  methods: {
    getList () {
      this.$http.get('/api/').then((res) => {
        if (!res.data.flag) {
          this.$message.error('获取todo列表失败，网络状况或服务部署异常')
          return
        }
        this.eventList = res.data.data
        this.getFilteredList(res.data.data)
      })
    },

    getFilteredList (list) {
      if (this.flagAll) {
        this.filteredList = list
      } else if (this.flagCompleted) {
        this.filteredList = list.filter((item) => {
          return item.done
        })
      } else {
        this.filteredList = list.filter((item) => {
          return !item.done
        })
      }
    },

    remove (id) {
      this.$http
        .delete('/api/' + id)
        .then((res) => {
          // 判断该操作是否成功
          if (res.data.flag) {
            this.$message.success('删除事项成功')
          } else {
            this.$message.error('删除事项失败')
          }
        })
        .finally(() => {
          // 重新从后端读取完整的todo事件列表
          this.getList()
        })
    },
    update (id) {
      this.$http
        .get('/api/' + id)
        .then((res) => {
          // 判断该操作是否成功
          if (res.data.flag) {
            this.$message.success('修改事项完成状态成功')
          } else {
            this.$message.error('修改事项完成状态失败')
          }
        })
        .finally(() => {
          // 重新从后端读取完整的todo事件列表
          this.getList()
        })
    },

    addTodo (todo) {
      // 控制台打印一下收到的数据，测试用
      // console.log('我是index组件，我收到了数据:', todo)
      // 将该数据添加到后端数据库中
      this.$http
        .post('/api/', todo)
        .then((res) => {
          // 判断该操作是否成功
          if (res.data.flag) {
            this.$message.success('添加事项成功')
          } else {
            this.$message.error('添加事项失败')
          }
        })
        .finally(() => {
          // 重新从后端读取完整的todo事件列表
          this.getList()
        })
    },
    showTab (tabId) {
      if (tabId === 1) {
        this.flagAll = true
        this.flagActive = false
        this.flagCompleted = false
        this.$message.success('切换到全部事项视图')
      } else if (tabId === 2) {
        this.flagAll = false
        this.flagActive = true
        this.flagCompleted = false
        this.$message.success('切换到未完成事项视图')
      } else {
        this.flagAll = false
        this.flagActive = false
        this.flagCompleted = true
        this.$message.success('切换到已完成事项视图')
      }
      this.getFilteredList(this.eventList)
    },
    clearCompleted () {
      console.log('把所有done为true的事项的deleted也设置为true')
      this.$http
        .delete('/api/')
        .then((res) => {
          // 判断该操作是否成功
          if (res.data.flag) {
            this.$message.success('删除事项成功')
          } else {
            this.$message.error('当前没有已完成的可供删除的事件')
          }
        })
        .finally(() => {
          // 重新从后端读取完整的todo事件列表
          this.getList()
        })
    }
  },
  created () {
    this.getList()
  }
}
</script>

<style lang="less" scoped>
body {
  background-color: wheat;
}
.todo_container {
  background-color: wheat;
  height: auto;
  margin-bottom: 100px;
}

.todo_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 20%;
  transform: translate(-50%, -50%);

  .avatar_box {
    height: 130px;
    width: 130px;
    img {
      height: 100%;
      width: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
  }

  .btns {
    display: flex;
    justify-content: flex-end;
  }

  .todo_form {
    position: absolute;
    top: 100px;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
  }
}
</style>
