<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create company" : "View company" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="company.name" label="Name" v-bind:readonly=!isAdmin />
          <v-text-field v-model="company.description" label="Description" v-bind:readonly=!isAdmin />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist" v-show="isAdmin&isNew"> 
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deleteCompany" v-show="isAdmin"> Delete </v-btn>
          <v-btn @click="setVisible"> Close </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "CompanyDialog",
  props: {
    company: Object,
    opened: Boolean,
    isAdmin: Boolean
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.companies
          .create({
            name: this.company.name,
            description: this.company.description,
          })
          .then(() => this.$emit("refresh"));
        
      } else {
        api.companies
          .edit({
            id: this.company.id,
            name: this.company.name,
            description: this.company.description,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deleteCompany(){
      api.companies
        .delete({
          id: this.company.id,
      })
      .then(() => this.$emit("refresh"));
    },
    setVisible(){
      this.$emit("refresh");
    }
  },
  computed: {
    isNew: function () {
      return !this.company.id;
    },
  },
};
</script>

<style scoped></style>
