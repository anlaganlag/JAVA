<template>
  <el-card>
    <el-row>
      <el-col :span="12">

        <el-form ref="form" :model="sysOsInfo" label-width="100px" style="padding: 0 50px">

          <el-form-item label="系统名称">
            <el-input v-model="sysOsInfo.osName"
                      :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="系统架构">
            <el-input v-model="sysOsInfo.osArch"
                      :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="系统版本">
            <el-input v-model="sysOsInfo.osVersion"
                      :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="主机名称">
            <el-input v-model="sysOsInfo.osHostName"
                      :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="主机IP地址">
            <el-input v-model="sysOsInfo.osHostAddress"
                      :disabled="true">
            </el-input>
          </el-form-item>

        </el-form>
      </el-col>

      <el-col :span="12">

        <el-form ref="form" :model="sysJavaInfo" label-width="100px" style="padding: 0 50px">

          <el-form-item label="虚拟机名称">
            <el-input v-model="sysJavaInfo.javaName"
                      :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="虚拟机版本">
            <el-input v-model="sysJavaInfo.javaVersion"
                      :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="虚拟机供应商">
            <el-input v-model="sysJavaInfo.jvmName"
                      :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="java名称">
            <el-input v-model="sysJavaInfo.jvmVendor"
                      :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="java版本">
            <el-input v-model="sysJavaInfo.jvmVersion"
                      :disabled="true">
            </el-input>
          </el-form-item>

        </el-form>
      </el-col>
    </el-row>

    <el-divider></el-divider>

    <el-form ref="form" :model="sysJvmMemInfo" label-width="100px" style="padding: 0 50px">
      <el-form-item label="最大内存">
        <el-input v-model="sysJvmMemInfo.jvmMaxMemory"
                  :disabled="true">
        </el-input>
      </el-form-item>

      <el-form-item label="总内存">
        <el-input v-model="sysJvmMemInfo.jvmTotalMemory"
                  :disabled="true">
        </el-input>
      </el-form-item>

      <el-form-item label="空余内存">
        <el-input v-model="sysJvmMemInfo.jvmUsableMemory"
                  :disabled="true">
        </el-input>
      </el-form-item>

      <el-form-item label="可用内存">
        <el-input v-model="sysJvmMemInfo.jvmFreeMemory"
                  :disabled="true">
        </el-input>
      </el-form-item>

      <el-form-item label="已使用内存">
        <el-input v-model="sysJvmMemInfo.jvmUsedMemory"
                  :disabled="true">
        </el-input>
      </el-form-item>

      <el-form-item label="使用率">
        <el-input v-model="sysJvmMemInfo.jvmMemoryUsedRate"
                  :disabled="true">
        </el-input>
      </el-form-item>

    </el-form>
  </el-card>

</template>

<script>

  import HardwareInfo from "@/api/monitor/HardwareInfo";

  export default {
    data() {
      return {
        sysOsInfo: {
          osArch: "",
          osHostAddress: "",
          osHostName: "",
          osName: "",
          osVersion: ""
        },
        sysJavaInfo: {
          javaName: "",
          javaVersion: "",
          jvmName: "",
          jvmVendor: "",
          jvmVersion: ""
        },
        sysJvmMemInfo: {
          jvmFreeMemory: "",
          jvmMaxMemory: "",
          jvmMemoryUsedRate: "",
          jvmTotalMemory: "",
          jvmUsableMemory: "",
          jvmUsedMemory: ""
        },
      }
    },
    created() {
      this.initData()
    },
    methods: {
      initData() {
        let http = new HardwareInfo()

        http.getTotalInfo({}).then(res => {
          this.sysOsInfo = res.data.sysOsInfo
          this.sysJavaInfo = res.data.sysJavaInfo
          this.sysJvmMemInfo = res.data.sysJvmMemInfo
        })
      },
    }
  }
</script>
