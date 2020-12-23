<template>
  <div>
    <div style="text-align: left">
      <el-button type="success" @click="to"> 返回</el-button>
    </div>
    <el-form :model="form" :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
      <el-form-item label="展厅编号" prop="id">
        <el-input v-model="form.id" :disabled=isUpdate></el-input>
      </el-form-item>
      <el-form-item label="中文名称" prop="cnName">
        <el-input v-model="form.cnName"></el-input>


      </el-form-item>
      <el-form-item label="英文名称" prop="enName">
        <el-input v-model="form.enName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">{{buttonTitle}}</el-button>
        <el-button @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "ExhibitionHallManager",
    data() {
      return {
        form: {
          id: '',
          cnName: '',
          enName: '',

        },
        srcList: [],

        rules: {
          id: [
            {required: true, message: '展厅id', trigger: 'blur'},
            {min: 1, max: 3, message: '编号为两个字符', trigger: 'blur'}
          ],
          cnName: [
            {required: true, message: '请输入中文名称', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ],
          enName: [
            {required: true, message: '请输入英文名称', trigger: 'blur'},
            {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur'}
          ]
        },

        buttonTitle: "",
        isUpdate: true
      };
    },
    methods: {
      to() {
        this.$router.push({path: "/exhibitionhall/exhibitionhallList"});
      },

      submitForm(form) {
        this.$refs[form].validate((valid) => {
          if (valid) {
            if (this.isUpdate){
              this.update(this);
            }else{
              this.add(this);
            }
          } else {
            this.$message({
              message: '请填写完整信息再后提交',
              type: 'error'
            });
            return false;
          }
        });
      },
      update(_this){
        this.$http.post("exhibitionHall/updateExhibitionHall", this.form).then((res) => {
          if (res.data.success) {
            this.$message({
                message: "更新成功",
                type: 'success',
              }
            );
            setTimeout(function () {
              _this.to();
            }, 500)

          } else {
            this.$message({
              message: res.data.msg,
              type: 'error'
            });
          }
        })
      },
      add(_this){
        this.$http.post("exhibitionHall/addExhibitionHall", this.form).then((res) => {
          if (res.data.success) {

            this.$message({
                message: "创建成功",
                type: 'success',
              }
            );
            setTimeout(function () {
              _this.to();
            }, 500)

          } else {
            this.$message({
              message: res.data.msg,
              type: 'error'
            });
          }
        })
      },


      resetForm(form) {
        this.$refs[form].resetFields();
        if (this.isUpdate) {
          this.form.id = this.$route.query.id
        }
      }
    },
    created() {
      if (this.$route.query.id != null) {
        this.buttonTitle = "更新";
        this.isUpdate = true;
        this.$http.get("exhibitionHall/findById?id=" + this.$route.query.id).then((res) => {
          this.form= res.data;
        })
      } else {
        this.buttonTitle = "创建";
        this.isUpdate = false;
      }

    }
  }
</script>

<style scoped>
  .myelement {
    text-align: left
  }

  .input_width {
    width: 50%;
    width: 300px;
  }

  fieldset {
    border: 2px solid #DCDFE6;
    text-align: left;
    border-radius: 8px;
  }
</style>
