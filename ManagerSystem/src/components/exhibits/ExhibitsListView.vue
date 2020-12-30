<template>
  <div>
    <div>
      <el-button type="primary" @click="toAddExhibits()" round>添加</el-button>
    </div>
    <el-table
      :data="tableData.filter(data => !search || data.cnName.toLowerCase().includes(search.toLowerCase())||
      data.number.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100% ; margin-top: 10px"
      stripe fit show-header>
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
        width="550%"
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
            @click="EditImage(scope.$index, scope.row)">编辑图片
          </el-button>
          <el-button
            size="mini"
            type="success"
            @click="EditCnAudio(scope.$index, scope.row)">编辑中文配音
          </el-button>
          <el-button
            size="mini"
            type="success"
            @click="EditEnAudio(scope.$index, scope.row)">编辑英文配音
          </el-button>

          <el-button
            size="mini"
            type="success"
            @click="EditInfo(scope.$index, scope.row)">编辑信息
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
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

          :current-page.sync="pageNow"
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
      EditInfo(index, row) {
        this.$router.push({path: "/exhibits/exhibitsToUpdate?number=" + row.number});
      },
      EditImage(index, row) {
        this.$router.push({path: "/exhibits/exhibitsToUpdateFile?number=" + row.number + "&isImage=true"});
      },
      EditCnAudio(index, row){
        this.$router.push({path: "/exhibits/exhibitsToUpdateFile?number=" + row.number + "&isCnAudio=true"});
      },
      EditEnAudio(index, row){
        this.$router.push({path: "/exhibits/exhibitsToUpdateFile?number=" + row.number + "&isEnAudio=true"});
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
        this.pageNow = page;
        this.findAll(page, this.pageSize)
      },
      findSize(size) {
        this.pageSize = size;
        this.findAll(this.pageNow, size)
      }
    },

    created() {
      this.pageNow = Number(localStorage.getItem('pagination'))||1;

      this.findAll( this.pageNow,this.pagesize);
    },
    beforeUpdate() {
      //刷新页面保留当前分页，通过本地存储当前页
      localStorage.setItem('pagination',this.pageNow);
    },
    beforeDestroy() {
      localStorage.setItem('pagination',1);
    }
  }
</script>

<style scoped>

</style>
