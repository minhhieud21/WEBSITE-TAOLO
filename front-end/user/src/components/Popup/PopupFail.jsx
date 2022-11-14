import { toast } from "react-toastify"

export const PopUpFail = () => {
    toast.error("Thêm giỏ hàng thất bại!", {
        position: toast.POSITION.TOP_RIGHT,
    })
}