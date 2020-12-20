<template>
  <div>
    <div style="text-align: left">
      <el-button type="success" @click="to"> 返回</el-button>
    </div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="展品编号" prop="number">
        <el-input v-model="ruleForm.number"></el-input>
      </el-form-item>
      <el-form-item label="展品名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item ref="upload_attach_item_iamge" label="展示图片" prop="image" size='small'>
        <el-upload style="float: left"
                   ref="upload_attach"
                   class="upload-demo"
                   action="http://localhost:8080/manager/addExhibits"
                   multiple
                   accept=".jpg,.png,.jpeg"
                   :limit="1"
                   :on-change="changFileImage"
                   :on-exceed="handleExceed"
                   :on-remove="removeImageFile"
                   :file-list="imageFileList"
                   :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">注:上传图片不能超过1M</div>
        </el-upload>
        <el-progress :percentage="progressPercent" v-show="show_progress"></el-progress>

      </el-form-item>
      <el-form-item ref="upload_attach_item_cnAudio" label="中文配音" prop="cnAudio">
        <el-upload style="float: left"
                   ref="upload_attach"
                   class="upload-demo"
                   action="http://localhost:8080/manager/addExhibits"
                   multiple
                   accept=".mp3,.exe"
                   :limit="1"
                   :on-change="changeCnAudio"
                   :on-exceed="handleExceed"
                   :on-remove="removeCnAudioFile"
                   :file-list="cnAudioFileList"
                   :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">注:上传mp3不能超过1M</div>
        </el-upload>
      </el-form-item>
      <el-form-item ref="upload_attach_item_enAudio" label="英文配音" prop="enAudio">
        <el-upload style="float: left"
                   ref="upload_attach"
                   class="upload-demo"
                   action="http://localhost:8080/manager/addExhibits"
                   multiple
                   accept=".mp3,.exe"
                   :limit="1"
                   :on-change="changeEnAudio"
                   :on-exceed="handleExceed"
                   :on-remove="removeEnAudioFile"
                   :file-list="enAudioFileList"
                   :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">注:上传mp3不能超过1M</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="展品描述" prop="desc">
        <el-input type="textarea" v-model="ruleForm.desc"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import loading from "../../loading";
  import http from "../../http";


  export default {

    name: "ExhibitsAdd",
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
          name: '',
          desc: ''
        },
        imageFileList: [],
        cnAudioFileList: [],
        enAudioFileList: [],
        progressPercent: 0,
        show_progress: false,

        rules: {
          number: [
            {required: true, message: '请输入展品编号', trigger: 'blur'},
            {min: 3, max: 3, message: '编号为三个字符', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '展品名称', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
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
          desc: [
            {required: false, message: '请填写活动形式', trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      to() {
        this.$router.push({path: "/exhibits/exhibitsList"});
      },
      changFileImage(file, imageFileList) {
        console.log("changFile");
        console.log(imageFileList);
        //选择文件后，给fileList对象赋值
        this.imageFileList = imageFileList
        this.$refs.upload_attach_item_iamge.validate();

      },
      changeCnAudio(file, cnAudioList) {
        console.log("changeCnAudio");
        console.log(cnAudioList);
        //选择文件后，给fileList对象赋值
        this.cnAudioFileList = cnAudioList
        this.$refs.upload_attach_item_cnAudio.validate();

      }, changeEnAudio(file, enAudioList) {
        console.log("changeEnAudio");
        console.log(enAudioList);
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
            for (let key in this.ruleForm) {
              data.append(key, this.ruleForm[key])
            }
            const _loading = loading(`文件上传中，请稍后...`)

            // this.show_progress = true
            const config = {
              onUploadProgress: progressEvent => {
                // progressEvent.loaded:已上传文件大小
                // progressEvent.total:被上传文件的总大小
                this.progressPercent = Number((progressEvent.loaded / progressEvent.total * 100).toFixed(0))
                _loading.setText('作品上传中，进度：' + this.progressPercent + "%") //更新dialog进度，优化体验
                console.log(this.progressPercent)
              },
              headers: {
                'Content-Type': 'multipart/form-data'
              }
            }
            this.$http.post("http://localhost:8080/manager/addExhibits", data, config).then((res) => {

              console.log('请求本地接口OK')
              console.log(res)
              this.imageFileList = [];// 提交完成清空附件列表
              this.cnAudioFileList = [];// 提交完成清空附件列表
              this.enAudioFileList = [];// 提交完成清空附件列表
              _loading.close(); // 关闭加载框
              // this.show_progress = false
              this.progressPercent = 0
              console.log(res.data.success)

              if (res.data.success==true) {
                console.log(res.data.success)
                this.$message({
                    message: "添加成功",
                    type: 'success',
                  }
                );
                setTimeout(function () {
                  _this.to();
                }, 1500)
              } else {
                this.$message({
                  message: res.data.msg,
                  type: 'error'
                });
              }
            }).catch(function (error) { // 请求失败处理
              console.log('请求本地接口失败' + error);
            });
          } else {
            this.$message({
              message: '请填写完整信息再后提交',
              type: 'error'
            });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.imageFileList = [];
        this.cnAudioFileList = [];
        this.enAudioFileList = [];
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
