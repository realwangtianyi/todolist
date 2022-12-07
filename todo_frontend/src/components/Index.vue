<template>
  <div class="login_container">
    <div class="login_box" style="height: auto">
      <!-- 头像区 -->
      <div class="avatar_box">
        <img src="../assets/logo.png" />
      </div>
      <!-- todo主体区 -->
      <div class="todo_form" style="height: auto">
        <todo-header :addTodo='addTodo'></todo-header>
        <todo-list :eventList="eventList" style="margin-top: 20px"></todo-list>
      </div>
    </div>
  </div>
</template>

<script>
import TodoHeader from './TodoHeader.vue'
import TodoList from './TodoList.vue'
export default {
  components: { TodoHeader, TodoList },
  data () {
    return {
      eventList: [
        { id: '001', name: '去王克桢楼', done: true },
        { id: '002', name: '去吃饭', done: true }
      ],

      // 这是登录表单的数据绑定对象
      loginForm: {
        username: '',
        password: ''
      },
      loginFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    getList () {
      this.$http.get('/index').then((res) => {
        console.log(res.data)
      })
    },
    addTodo (todo) {
      console.log('我说app组件，我收到了数据:', todo)
      this.eventList.unshift(todo)
    }
  },
  created () {
    this.getList()
  }
}
</script>

<style lang="less" scoped>
.login_container {
  background-color: wheat;
  height: 100%;
}

.login_box {
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
