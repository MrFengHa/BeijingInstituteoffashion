<template>
  <div>
    <div>
      <el-button type="primary" @click="toAddExhibits()" round>添加</el-button>
    </div>
    <el-table
      :data="tableData.filter(data => !search || data.cnName.toLowerCase().includes(search.toLowerCase())||data.number.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100% ; margin-top: 10px">
      <el-table-column
        label="编号"
        prop="number">
      </el-table-column>
      <el-table-column
        label="中文名称"
        prop="cnName">
      </el-table-column>
      <el-table-column
        label="英文名称"
        prop="enName">
      </el-table-column>
      <el-table-column
        label="所处展馆"
        prop="exhibitionHall.cnName">
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
            @click="handleEdit(scope.$index, scope.row)">Edit
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row>
      <el-col :span="12" :offset="5" style="margin-top: 20px">
        <el-pagination
          background
          layout="prev, pager, next,jumper,sizes,total"
          :page-size="pageSize"
          :current-page="pageNow"
          :page-sizes="[10,20]"
          :total="total"
          @current-change="findPage"
          @size-change="findSize"
        >
        </el-pagination>
      </el-col>
    </el-row>

  </div>

</template>
<script>
  export default {
    name: "ContentListView",
    data() {
      return {
        tableData: [],
        search: '',
        total: 0,
        pageSize: 10,
        pageNow: 1
      }
    },
    methods: {
      toAddExhibits() {
        this.$router.push({path: "/exhibits/exhibitsToAdd"});
      },
      findAll(page, size) {
        let _this = this;
        page = page ? page : this.pageNow;
        size = size ? size : this.pageSize;
        this.$http.get("exhibits/findByPage?pageNow=" + page + "&pageSize=" + size).then((res) => {
          _this.tableData = res.data.exhibitsList;
          _this.total = res.data.totals;
        })
      },

      handleEdit(index, row) {
        this.$router.push({path: "/exhibits/exhibitsToUpdate?number=" + row.number});
      },
      handleDelete(index, row) {
        let _this = this;
        this.$http.post("exhibits/deleteExhibits", row).then((res) => {
          if (res.data.success == true) {
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
      },
      findPage(page) {//用来处理分页的相关方法
        console.log(page)
        console.log(this.pageSize)
        this.pageNow = page;
        this.findAll(page, this.pageSize)
      },
      findSize(size) {
        this.pageSize = size;
        this.findAll(this.pageNow, size)
      }
    },
    created() {
      this.findAll();
    }
  }
</script>

<style scoped>

</style>
