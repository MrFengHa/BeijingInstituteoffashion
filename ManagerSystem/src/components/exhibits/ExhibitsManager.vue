<template>
  <div>
    <div style="text-align: left">
      <el-button type="success" @click="to"> 返回</el-button>
    </div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="展品编号" prop="number">
        <el-input v-model="ruleForm.number" :disabled=isUpdate></el-input>
      </el-form-item>
      <el-form-item label="中文名称" prop="cnName">
        <el-input v-model="ruleForm.cnName"></el-input>
      </el-form-item>
      <el-form-item label="英文名称" prop="enName">
        <el-input v-model="ruleForm.enName"></el-input>
      </el-form-item>

      <el-form-item label="所处展厅" prop="exhibitionHallId">
        <el-select v-model="ruleForm.exhibitionHallId" clearable placeholder="请选择" style="float: left;width:100%">
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.cnName"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>


      <el-form-item ref="upload_attach_item_iamge" label="展示图片" prop="image" size='small' v-if="!isUpdate">
        <el-upload style="float: left"
                   ref="upload_attach"
                   class="upload-demo"
                   action=""
                   multiple
                   accept=".jpg,.png,.jpeg"
                   :limit="1"
                   :on-change="changFileImage"
                   :on-exceed="handleExceed"
                   :on-remove="removeImageFile"
                   :file-list="imageFileList"
                   :auto-upload="false"
        >

          <!--          <div style="width:5vh;margin-top: 5px">-->
          <!--            <el-image v-if=isUpdate  fit="fill" :src="ruleForm.imagePath" :preview-src-list="srcList" ></el-image>-->
          <!--          </div>-->
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">注:上传图片不能超过10M</div>
        </el-upload>
        <el-progress :percentage="progressPercent" v-show="show_progress"></el-progress>

      </el-form-item>
      <el-form-item ref="upload_attach_item_cnAudio" label="中文配音" prop="cnAudio" v-if="!isUpdate">
        <el-upload style="float: left"
                   ref="upload_attach"
                   class="upload-demo"
                   action=""
                   multiple
                   accept=".mp3"
                   :limit="1"
                   :on-change="changeCnAudio"
                   :on-exceed="handleExceed"
                   :on-remove="removeCnAudioFile"
                   :file-list="cnAudioFileList"
                   :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">注:上传mp3不能超过10M</div>
        </el-upload>
      </el-form-item>
      <el-form-item ref="upload_attach_item_enAudio" label="英文配音" prop="enAudio" v-if="!isUpdate">
        <el-upload style="float: left"
                   ref="upload_attach"
                   class="upload-demo"
                   action=""
                   multiple
                   accept=".mp3,"
                   :limit="1"
                   :on-change="changeEnAudio"
                   :on-exceed="handleExceed"
                   :on-remove="removeEnAudioFile"
                   :file-list="enAudioFileList"
                   :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">注:上传mp3不能超过10M</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="中文描述" prop="cnDesc">
        <el-input type="textarea" autosize v-model="ruleForm.cnDesc"></el-input>
      </el-form-item>
      <el-form-item label="英文描述" prop="enDesc">
        <el-input type="textarea" autosize v-model="ruleForm.enDesc"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')" v-if="!isUpdate">{{buttonTitle}}</el-button>
        <el-button type="primary" @click="updateExhibits()" v-if="isUpdate">{{buttonTitle}}</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import loading from "../../loading";


  export default {
    name: "ExhibitsManager",
    data() {
      //验证密码
      var validateAttachImage = (rule, value, callback) => {

        if (this.imageFileList.length == 0) {
          callback(new Error('请选择展示图片'));
        } else {
          callback();
        }
      };
      //验证密码
      var validateAttachCnAudio = (rule, value, callback) => {

        if (this.cnAudioFileList.length == 0) {
          callback(new Error('请选择中文配音'));
        } else {
          callback();
        }
      };
      //验证密码
      var validateAttachEnAudio = (rule, value, callback) => {

        if (this.enAudioFileList.length == 0) {
          callback(new Error('请选择英文配音'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          number: '',
          cnName: '',
          enName: '',
          exhibitionHallId: '',
          cnDesc: '',
          enDesc: '',
          imagePath: '',
          cnAudioPath: '',
          enAudioPath: ''

        },
        srcList: [],
        imageFileList: [],
        cnAudioFileList: [],
        enAudioFileList: [],
        progressPercent: 0,
        show_progress: false,

        options: [],
        rules: {
          number: [
            {required: true, message: '请输入展品编号', trigger: 'blur'},
            {min: 4, max: 4, message: '编号为四个字符', trigger: 'blur'}
          ],
          cnName: [
            {required: true, message: '请输入中文名称', trigger: 'blur'},
            {min: 1, max: 50, message: '长度在 0 到 50 个字符', trigger: 'blur'}
          ],
          enName: [
            {required: true, message: '请输入英文名称', trigger: 'blur'},
            {min: 1, max: 200, message: '长度在 0 到 200 个字符', trigger: 'blur'}
          ],
          exhibitionHallId: [
            {required: true, message: '请为展品选择展厅', trigger: 'change'}
          ],
          image: [
            // {  message: '请选择展品图片', trigger: 'blur' },
            {required: true, validator: validateAttachImage}
          ],
          cnAudio: [
            // {required: true, message: '请选择展品中文配音', trigger: 'blur'},
            {required: true, validator: validateAttachCnAudio}
          ],
          enAudio: [
            //{required: true, message: '请选择展品英文配音', trigger: 'blur'},
            {required: true, validator: validateAttachEnAudio}

          ],
          cnDesc: [
            {required: true, message: '请输入中文描述', trigger: 'blur'}
          ],
          enDesc: [
            {required: true, message: '请输入英文描述', trigger: 'blur'}
          ]
        },

        buttonTitle: "",
        isUpdate: true
      };
    },
    methods: {
      to() {
        this.$router.push({path: "/exhibits/exhibitsList"});
      },
      changFileImage(file, imageFileList) {
        //选择文件后，给fileList对象赋值
        this.imageFileList = imageFileList
        this.$refs.upload_attach_item_iamge.validate();

      },
      changeCnAudio(file, cnAudioList) {
        //选择文件后，给fileList对象赋值
        this.cnAudioFileList = cnAudioList
        this.$refs.upload_attach_item_cnAudio.validate();

      }, changeEnAudio(file, enAudioList) {
        //选择文件后，给fileList对象赋值
        this.enAudioFileList = enAudioList
        this.$refs.upload_attach_item_enAudio.validate();

      }, removeImageFile(file, fileList) {
        this.imageFileList = fileList
        this.$refs.upload_attach_item;
      },
      removeCnAudioFile(file, fileList) {
        this.cnAudioFileList = fileList
        this.$refs.upload_attach_item;
      },
      removeEnAudioFile(file, fileList) {
        this.enAudioFileList = fileList
        this.$refs.upload_attach_item;
      },
      handleExceed(image, fileList) {
        this.$message.warning(`当前限制最多选择 1 个文件`);
      },
      submitForm(formName) {

        let _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let data = new FormData();
            data.append("image", this.imageFileList[0].raw);
            data.append("cnAudio", this.cnAudioFileList[0].raw);
            data.append("enAudio", this.enAudioFileList[0].raw);
            data.delete("exhibitionHall");
            for (let key in this.ruleForm) {
              if (key != "exhibitionHall") {
                data.append(key, this.ruleForm[key])
              }
            }
            const _loading = loading(`文件上传中，请稍后...`)

            // this.show_progress = true
            const config = {
              onUploadProgress: progressEvent => {
                // progressEvent.loaded:已上传文件大小
                // progressEvent.total:被上传文件的总大小
                this.progressPercent = Number((progressEvent.loaded / progressEvent.total * 100).toFixed(0))
                _loading.setText('文件上传中，进度：' + this.progressPercent + "%") //更新dialog进度，优化体验
              },
              headers: {
                'Content-Type': 'multipart/form-data'
              }
            }


            this.addExhibits(data, _loading, config, _this)


          } else {
            this.$message({
              message: '请填写完整信息再后提交',
              type: 'error'
            });
            return false;
          }
        });
      },

      updateExhibits() {

         let data = new FormData();
         for (let key in this.ruleForm) {
           if (key != "exhibitionHall") {
            data.append(key, this.ruleForm[key])
          }
         }

        let _this = this;
        this.$http.post("exhibits/updateExhibitsInfo",data).then((res) => {

          if (res.data.success == true) {

            this.$message({
                message: "更新成功",
                type: 'success',
              }
            );
            setTimeout(function () {
              _this.to();

            }, 100)

          } else {
            this.$message({
              message: res.data.msg,
              type: 'error'
            });
          }
        }).catch(function (error) { // 请求失败处理
        });
      },

      addExhibits(data, _loading, config, _this) {
        this.$http.post("exhibits/addExhibits", data, config).then((res) => {


          _loading.close(); // 关闭加载框
          // this.show_progress = false
          this.progressPercent = 0
          if (res.data.success == true) {

            this.$message({
                message: "创建成功",
                type: 'success',
              }
            );
            setTimeout(function () {
              _this.imageFileList = [];// 提交完成清空附件列表
              _this.cnAudioFileList = [];// 提交完成清空附件列表
              _this.enAudioFileList = [];// 提交完成清空附件列表
              _this.to();

            }, 100)

          } else {
            this.$message({
              message: res.data.msg,
              type: 'error'
            });
          }
        }).catch(function (error) { // 请求失败处理
        });
      },

      resetForm(formName) {
        this.$refs[formName].resetFields();
        if (this.isUpdate) {
          this.ruleForm.number = this.$route.query.number
        }
        this.imageFileList = [];
        this.cnAudioFileList = [];
        this.enAudioFileList = [];
      }
    },
    created() {
      let _this = this;
      this.$http.get("exhibitionHall/findAll").then(res => {

        _this.options = res.data
      })


      if (this.$route.query.number != null) {
        this.buttonTitle = "更新";
        this.isUpdate = true;

        this.$http.get("exhibits/findById?number=" + this.$route.query.number).then((res) => {
          _this.ruleForm = res.data;
          _this.srcList.push(res.data.imagePath);
        })

      } else {
        this.buttonTitle = "创建";
        this.isUpdate = false;
      }

    }
  }
</script>

<style scoped>

  fieldset {
    border: 2px solid #DCDFE6;
    text-align: left;
    border-radius: 8px;
  }
</style>
