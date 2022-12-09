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
          <el-checkbox v-model='scope.row.done' @change="update(scope.row.id)"></el-checkbox>
        </template>
      </el-table-column>
      <el-table-column label='操作' align='center' width='70px'>
        <template v-slot='scope'>
          <!-- 删除按钮 -->
          <el-button
            type='danger'
            icon='el-icon-delete'
            size='mini'
            @click='remove(scope.row.id)'
          ></el-button>
        </template>
      </el-table-column>

      <template slot="empty">
        <el-empty description='暂无数据'></el-empty>
      </template>

    </el-table>
  </div>
</template>

<script>
export default {
  name: 'TodoList',
  props: ['eventList'],
  data () {
    return {
    }
  },
  methods: {
    update (id) {
      console.log('执行update', id)
      this.$emit('update', id)
    },
    remove (id) {
      console.log('执行remove', id)
      this.$emit('remove', id)
    },
    tableRowClassName ({ row, rowIndex }) {
      // console.log('@row:', row)
      // console.log('@rowIndex:', rowIndex)
      if (row.done) {
        return 'deleted'
      } else {
        return ''
      }
    }
  },
  created () {
    // this.getEventList()
  }
}
</script>

<style>
.deleted {
  text-decoration: line-through;
  color: lightgray;
}
</style>
