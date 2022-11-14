import { toast } from "react-toastify"

export const PopupSuccess = (text) => {
	return toast.success(text, {
		position: toast.POSITION.BOTTOM_CENTER,
	})
}
