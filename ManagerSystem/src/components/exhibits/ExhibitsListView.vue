<template>
  <div>
    <div>
        <el-button type="primary"  @click="to()" round>添加</el-button>
      </div>
    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100% ; margin-top: 10px">
      <el-table-column
        label="编号"
        prop="number">
      </el-table-column>
      <el-table-column
        label="名称"
        prop="name">
      </el-table-column>

      <el-table-column
        align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>
<script>
  export default {
    name: "ContentListView",
    data() {
      return {
        tableData: [],
        search: ''
      }
    },
    methods: {
      to() {
        this.$router.push({path: "/exhibits/exhibitsAdd"});
      },
      findAll(){
        let _this = this;
        this.$http.get("http://localhost:8080/manager/findAll").then((res)=>{
          _this.tableData = res.data
        })
      },

      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        let _this = this;
        this.$http.post("http://localhost:8080/manager/deleteExhibits",row).then((res)=>{
          if (res.data.success==true) {
            console.log(res.data.success)
            this.$message({
                message: "删除成功",
                type: 'success',
              }
            );
            _this.findAll()
          } else {
            this.$message({
              message: "删除失败",
              type: 'error'
            });
          }
        })
      }
    },
    created() {
     this.findAll();
    }
  }
</script>

<style scoped>

</style>
