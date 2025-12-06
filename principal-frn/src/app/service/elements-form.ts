import { Injectable, ViewContainerRef } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ElementsForm {
  constructor() {}

  prevCarr(row: ViewContainerRef, containerID: any, hiddenEffect: boolean = false): void{
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

         //if (currentScroll > maxScroll) scrollPosition = -scrollPosition; //lllll - nao da para usar por conta de cair no scrollTo com o valor negativo ele voltara para a posicao 0!

         rowDOM.scrollTo({
             left: scrollPosition,
             behavior: hiddenEffect? "auto":"smooth"
         });
      }

      index++;
     });

     index = 0;
     nodes.forEach((node: any) =>{
      if (node.nodeType === Node.COMMENT_NODE) return;

      if(index === newposition){
         node.classList.remove("carr-off", "carr-on", "show-effecty", "hidden-effecty", "dnone");
         node.classList.add("carr-on");

         if(hiddenEffect) node.classList.add("show-effecty");
      }else{
         node.classList.remove("carr-off", "carr-on", "show-effecty", "hidden-effecty", "dnone");
         node.classList.add("carr-off");

         if(hiddenEffect) node.classList.add("hidden-effecty", "dnone");
      }

      index++;
     });
  }


  nextCarr(row: ViewContainerRef, containerID: any, hiddenEffect: boolean = false): void{
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
             behavior: hiddenEffect? "auto":"smooth"
         });
      }

      index++;
     });

     index = 0;
     nodes.forEach((node: any) =>{
      if (node.nodeType === Node.COMMENT_NODE) return;

      if(index === newposition){
         node.classList.remove("carr-off", "carr-on", "show-effecty", "hidden-effecty", "dnone");
         node.classList.add("carr-on");

         if(hiddenEffect) node.classList.add("show-effecty");
      }else{
         node.classList.remove("carr-off", "carr-on", "show-effecty", "hidden-effecty", "dnone");
         node.classList.add("carr-off");

         if(hiddenEffect) node.classList.add("hidden-effecty", "dnone");
      }

      index++;
     });
  }
}
