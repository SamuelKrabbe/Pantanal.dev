import { defineStore } from 'pinia'

export const useSocialContractsStore = defineStore('SocialContracts', {
    state: () => {
        return {
            contractsList: []
        }
    },
    getters:{
        get(){
            return this.contractsList
        },
        getById(id){
            this.contractsList.forEach(action => {
                if(action.id == id){
                    return action
                }
            });
        }
    },
    actions: {
        set(list){
            this.contractsList = list
        },
        add(contract){
            this.contractsList.push(contract)
        }
    }
})