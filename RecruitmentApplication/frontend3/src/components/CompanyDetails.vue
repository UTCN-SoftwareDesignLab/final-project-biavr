<template>
<div>
    <v-card>
        <v-text-field v-model="company.name" label="Name" v-bind:readonly = !this.editEnabled />
        <v-text-field v-model="company.description" label="Description" v-bind:readonly = !this.editEnabled />
    <v-card-actions>
        <v-btn @click="edit" v-show="isRecruiter"> Edit </v-btn>
        <v-btn @click="save" v-show="isRecruiter&editEnabled" > Save </v-btn>
    </v-card-actions>
    </v-card>
</div>
</template>

<script>
import api from "../api";

export default {
  name: "CompanyDetails",
  props: {
    company: Object,
    isRecruiter: Boolean,
  },
  data(){
      return {
          editEnabled : false,
      };
  },
  methods: {
    edit(){
        this.editEnabled = true;
    },
    save() {
        this.editEnabled = false;
        api.companies
        .edit({
            id: this.company.id,
            name: this.company.name,
            description: this.company.description,
            jobs: this.company.jobs,
            users: this.company.users
        })
        .then(() => {
            localStorage.setItem("company",JSON.stringify(this.company));
            this.$emit("refresh");
            });
    },
    
  },
  
};
</script>

<style scoped></style>
