import { defineStore } from 'pinia'

export const useSocialActionStore = defineStore('SocialActions', {
    state: () => {
        return {
            actionslist: []
        }
    },
    getters:{
        get(){
            return this.actionslist
        }
    },
    actions: {
        set(list){
            this.actionslist = list
        },
        add(socialaction){
            this.actionslist.push(socialaction)
        }
    }
})