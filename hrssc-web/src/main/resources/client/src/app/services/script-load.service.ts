import { Injectable } from '@angular/core';
import { ScriptStore } from '../models/LoadScript.model';

declare var document: any;

@Injectable()
export class ScriptLoadService {
  private scripts: any = {};
  constructor() {
    ScriptStore.forEach((script: any) => {
      this.scripts[script.name] = {
        loaded: false,
        src: script.src
      };
    });
  }

  load(...scripts: string[]) {
    var promises: any[] = [];
    scripts.forEach((script) => promises.push(this.loadScript(script)));
    return Promise.all(promises);
  }
  loadScript(name: string) {
    return new Promise((resolve, reject) => {
      //resolve if already loaded
      
        //load script
        let script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = this.scripts[name].src;
        document.getElementsByTagName('head')[0].appendChild(script);
      
    });
  }


}
