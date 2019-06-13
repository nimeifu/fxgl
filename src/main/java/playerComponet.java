import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

import static com.almasb.fxgl.app.DSLKt.geti;

public class playerComponet extends Component {
    private PositionComponent position;

    private double speed =1;
    private LocalTimer fireTimer=FXGL.newLocalTimer();
    private boolean canFire=true;
    private LocalTimer shootTimer= FXGL.newLocalTimer();
    private boolean canShoot=true;
    //private WeaponType weapon=WeaponType.slash;

    @Override
    public void onUpdate(double tpf)
    {

        speed =tpf*60;
        //position.translateX(tpf*FXGL.<Config>getGameConfig().getPlayerSpeed());

        if(fireTimer.elapsed(Duration.seconds(0.10))){
            canFire=true;
        }
        if(shootTimer.elapsed(Duration.minutes(0.30)))
        {
            canShoot=true;
        }
    }

    public void up()
    {
        if(entity.getY()<100)
        {
            position.translateY(- speed);
        }
        return;
    }

    public void down()
    {
        if(entity.getY()<633-speed)
        {
            position.translateY(speed);
        }
        return;
    }

    public void left(){
        if(entity.getX()>speed)
        {
            position.translateX(-speed);
        }
        return;
    }

    public void right(){
        if(entity.getX()<1024+speed){
            position.translateX(speed);
        }
        return;
    }

    public void fire(){
        if(geti("bullets")==0)
        {
            return;
        }
        if(!canFire)
        {
            return;
        }
        fireTimer.capture();
        canFire=false;
    }
}
