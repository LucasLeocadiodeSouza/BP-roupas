import { Injectable, ViewContainerRef } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ElementsForm {
  constructor() {}

  prevCarr(row: ViewContainerRef, containerID: any): void{
    const rowDOM = row.element.nativeElement;

    const widthContainer = rowDOM.offsetWidth;

    const nodes       = containerID.childNodes;
    const nodeslenght = nodes.length - 1;

    let index       = 0;
    let newposition = 0;

    const currentScroll = rowDOM.scrollLeft;
    const maxScroll     = rowDOM.scrollWidth - rowDOM.clientWidth;

    nodes.forEach((node: any) =>{
      if (node.nodeType === Node.COMMENT_NODE) return;

      const isActiveImage = node.classList.contains("carr-on") || currentScroll > maxScroll;

      if(isActiveImage){
         newposition = (index - 1) < 0? nodeslenght - 1:index - 1;

         let scrollPosition = newposition * widthContainer;

         if (currentScroll > maxScroll) scrollPosition = -scrollPosition;

         rowDOM.scrollTo({
             left: scrollPosition,
             behavior: "smooth"
         });
      }

      index++;
     });

     index = 0;
     nodes.forEach((node: any) =>{
      if (node.nodeType === Node.COMMENT_NODE) return;

      if(index === newposition){
         node.classList.remove("carr-off", "carr-on");
         node.classList.add("carr-on");
      }else{
         node.classList.remove("carr-off", "carr-on");
         node.classList.add("carr-off");
      }

      index++;
     });
  }


  nextCarr(row: ViewContainerRef, containerID: any): void{
    const rowDOM = row.element.nativeElement;

    const widthContainer = rowDOM.offsetWidth;

    const nodes       = containerID.childNodes;
    const nodeslenght = nodes.length - 1;

    let index       = 0;
    let newposition = 0;

    const currentScroll = rowDOM.scrollLeft;
    const maxScroll     = rowDOM.scrollWidth - rowDOM.clientWidth;

    nodes.forEach((node: any) =>{
      if (node.nodeType === Node.COMMENT_NODE) return;

      const isActiveImage = node.classList.contains("carr-on") || currentScroll > maxScroll;

      if(isActiveImage){
         newposition = (index + 1) > nodeslenght - 1?0:index + 1;

         const scrollPosition = newposition * widthContainer;
         rowDOM.scrollTo({
             left: scrollPosition,
             behavior: "smooth"
         });
      }

      index++;
     });

     index = 0;
     nodes.forEach((node: any) =>{
      if (node.nodeType === Node.COMMENT_NODE) return;

      if(index === newposition){
         node.classList.remove("carr-off", "carr-on");
         node.classList.add("carr-on");
      }else{
         node.classList.remove("carr-off", "carr-on");
         node.classList.add("carr-off");
      }

      index++;
     });
  }
}
