import React from 'react'

export default function DetailSliderProduct({ img, callBack }) {
    
    return (

        <div className="swiper-slide h-auto swiper-thumb-item mb-3">
            <img className="w-100" src={img} alt="..." onClick={() => callBack}/>
        </div>


    )
}
