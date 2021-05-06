<template>
  <iframe :src="iframeUrl" width="100%" height="100%" frameborder="0"></iframe>
</template>

<script>
  export default {
    name: 'nacosConfig',
    props: {
      width: {
        type: String,
        default: '80vw'
      },
      modelId: {
        type: String,
      },
    },
    data() {
      return {
        title: 'nacos配置中心',
        isShowVisible: true,
        iframeUrl: '',
      }
    },
    created() {
      window.open(`${process.env.VUE_APP_NACOS_CONFIG_URL}`)
    }
  }
</script>

<style scoped>

</style>
