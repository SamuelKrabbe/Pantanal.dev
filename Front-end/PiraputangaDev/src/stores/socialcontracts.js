import { defineStore } from 'pinia'

export const useSocialContractsStore = defineStore('SocialContracts', {
    state: () => {
        return {
            contractsList: [],
            volunteeredSocialActions: []
        }
    },
    getters:{
        get(){
            return this.contractsList
        },
        getVolunteered(){
            return this.volunteeredSocialActions
        }
    },
    actions: {
        set(list){
            this.contractsList = list
        },
        add(contract){
            this.contractsList.push(contract)
        },
        setVolunteered(actionList){
            this.volunteeredSocialActions = []
            for(let j = 0; j < this.contractsList.length; j++){
                for(let i = 0; i < actionList.length; i++){
                    if(actionList[i].id === this.contractsList[j].socialActionId){
                        this.volunteeredSocialActions.push(actionList[i])
                    }
                }
            }
        }
    }
})