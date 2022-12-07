<template>
  <div>
    <el-table
      :data='eventList'
      stripe
      style='width: 100%'
      :row-class-name='tableRowClassName'
    >
      <el-table-column prop='name' label='待办事项'></el-table-column>
      <el-table-column
        prop='done'
        label='是否完成'
        align='center'
        width='100px'
      >
        <template v-slot='scope'>
          <el-checkbox v-model='scope.row.done'></el-checkbox>
        </template>
      </el-table-column>
      <el-table-column label='操作' align='center' width='70px'>
        <template v-slot='scope'>
          <!-- 删除按钮 -->
          <el-button
            type='danger'
            icon='el-icon-delete'
            size='mini'
            @click='removeUserById(scope.row.id)'
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'TodoList',
  data () {
    return {
      eventList: null
      // eventList: [
      //   { id: '001', name: '去王克桢楼', done: true },
      //   { id: '002', name: '去吃饭', done: true }
      // ]
    }
  },
  methods: {
    async getEventList () {
      const { data: res } = await this.$http.get('index')
      if (!res.flag) {
        this.$message.error('获取todo列表失败!')
      }
      this.$message.success('获取todo列表成功!')
      // eslint-disable-next-line vue/no-mutating-props
      this.eventList = res.data
      // console.log(res)
    },
    // async addEvent () {
    //   const { data: res } = await this.$http.post('index', this.addForm)
    //   if (!res.flag) {
    //     return this.$message.error('todo添加失败了~')
    //   }
    //   // 添加成后重新获取todo数据,不需要用户手动刷新
    //   this.getEventList()
    //   return this.$message.success('todo添加成功了~')
    // },
    tableRowClassName ({ row, rowIndex }) {
      console.log('@row:', row)
      console.log('@rowIndex:', rowIndex)
      if (row.done) {
        return 'deleted'
      } else {
        return ''
      }
    }
  },
  // props: ['eventList'],
  created () {
    this.getEventList()
  }
}
</script>

<style>
.deleted {
  text-decoration: line-through;
  color: lightgray;
}
</style>
