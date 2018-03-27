import {NotificationService} from "./notification.service";
import {Injectable} from '@angular/core';


@Injectable()
export class NotificationComponent {
  constructor(private notificationService: NotificationService) {
    }
  
  showNotification(title: string,content: string, icon = "fa fa-bell" )
  {
    this.notificationService.smallBox({
      title: title,
      content: content,
      color: "#5384AF",
      timeout: 5000,
      icon: icon
    }); 
  }
  
  }
