<template>
  <div class="text-center">
    <v-dialog
        transition="dialog-bottom-transition"
        max-width="600"
        :value="opened"
    >
      <v-card>
        <v-toolbar color="primary" dark>
          Congratulations!
        </v-toolbar>

        <v-card-text>
          Your application was registered successfully. We will contact you for further details. You can find a copy of you application on your email.
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="close"
          >
            Okay
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
  export default {
    name: "ApplicationResult",
    props: {
        opened: Boolean,
    },
    methods: {
        close(){
            //close the dialog and redirect to company list
            this.$router.push({name:"Companies"});
        }
    }
  }
</script>