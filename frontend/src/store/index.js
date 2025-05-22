import {defineStore} from 'pinia'

export const userIdStore = defineStore(
    'userid',
    {
        state: ()=>({ uid: ''})
    }
)