package model;

import static java.lang.Math.PI;
import static java.lang.Math.asin;
import static java.lang.Math.tan;
import static java.lang.Math.cos;

/**
 *
 * @author Cassio Morales
 */
public class Converte {
    
    private int x_trilha_max;
    private int x_trilha_min;
    private int g_base_max;
    private int x_cam;
    private int x_cam_max;
    private int x_cam_min;
    private int y_cam_min;
    private int y_cam_max;
    private int y_cam;
    private int garra;
    private boolean lado; 
    private double angle_sum;
    private int step_sum;
    
    public Converte(int x_cam, int y_cam){
      this.x_trilha_max = 969;
      this.x_trilha_min = 95;
      this.g_base_max = 1019;
      this.y_cam_max = 250;
      this.y_cam_min = 5;
      this.x_cam_max = 560;
      this.x_cam_min = 0;
      this.garra = 320;
      this.x_cam = x_cam_max - x_cam;
      this.y_cam = y_cam_min - y_cam;
      verificaLado();
      if(lado) {
        if((y_cam) <= 80) {
          this.angle_sum = -0.01;
          this.step_sum = 80;
        }
        else if((y_cam) > 80 && (y_cam) <= 160) {
          this.angle_sum = 0.03;
          this.step_sum = 50;
        }
        else {
          this.angle_sum = 0.03;
          this.step_sum = 0;
        }
        if(x_cam >= 525)
          this.step_sum += 35;
      }
      else {
        if((y_cam) <= 80) {
          this.angle_sum = 0.32;
        }
        else if((y_cam) > 80 && (y_cam) <= 160) {
          this.angle_sum = 0.3;
        }
        else {
          this.angle_sum = 0.17;
        }
        if(x_cam <= 35)
          this.angle_sum -= 0.06;
        else if(x_cam > 35 && x_cam <= 130)
          this.angle_sum += 0.09;
        else
          this.angle_sum += 0.12;
      }
      System.out.println(x_cam + ", " + y_cam + " " + angle_sum);
    }
    
    private void verificaLado(){
        if(this.x_cam < (this.x_cam_max - this.x_cam_min)/2)
            this.lado = true; 
        else 
            this.lado = false;
    }
    
    public int[] calculaConversao(){
        int[] ret_x_g = {0,0};
        double angulo;
        double cateto_x = 0;
        
        /*if(this.y_cam >= this.y_cam_min){
            ret_x_g[0] = this.valorMaximo();
            ret_x_g[1] = 509;
            return ret_x_g;
        }
        else{*/
            if(!this.lado){
                angulo = asin(((double)this.y_cam-y_cam_min)/((double)this.garra)) - angle_sum;
                cateto_x = ((this.garra)*cos(angulo));
                ret_x_g[0] = valorTrilho(this.x_cam - (int)cateto_x);
                ret_x_g[1] = (int)this.calculaGraus(angulo);
                ret_x_g[1] += (ret_x_g[1]%2 == 0) ? 0 : 1;
                return ret_x_g;
            }
            else{
                angulo = asin(((double)this.y_cam-y_cam_min)/((double)this.garra)) + angle_sum;
                cateto_x = ((this.garra)*cos(angulo));
                ret_x_g[0] = valorTrilho(this.x_cam + (int)cateto_x) + 110 + step_sum;
                ret_x_g[1] = ((int)this.calculaGraus(-PI - angulo));
                ret_x_g[1] += (ret_x_g[1]%2 == 0) ? 0 : 1;
                return ret_x_g;
            }
        //}
    }

    private int valorMaximo() {
        return ((this.x_trilha_max * (this.x_cam-this.x_cam_min))/(this.x_cam_max-this.x_cam_min));
    }
    
    private int valorTrilho(int valor) {
      return (valor*(this.x_trilha_max - this.x_trilha_min))/(this.x_cam_max-this.x_cam_min);
    }

    private double calculaGraus(double angulo) {
        return ((this.g_base_max*angulo)/PI);
    }
}

