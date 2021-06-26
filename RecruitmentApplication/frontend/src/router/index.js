import Vue from "vue";
import VueRouter from "vue-router";
import UserList from "../views/UserList.vue";
import CompanyList from "../views/CompanyList.vue";
import JobList from "../views/JobList.vue";
import { auth as store } from "../store/auth.module";
import Login from "../views/Login";
import JobApplication from "../views/JobApplication"
import AdminFirstView from "../views/AdminFirstView";
import ApplicationsList from "../views/ApplicationsList";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/admin",
    name: "Admin",
    component: AdminFirstView,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "Companies" });
      }
    },
  },
  {
    path: "/companies",
    name: "Companies",
    component: CompanyList,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/companies/jobs",
    name: "Jobs",
    component: JobList,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/companies/jobs/applications/list",
    name: "Applications",
    component: ApplicationsList,
    props: {
      job : Object
    },
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/companies/jobs/applications",
    name: "JobApplication",
    component: JobApplication,
    props: {
      job : Object
    },
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
