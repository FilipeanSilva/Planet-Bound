package Logic.States;

public interface IState {

    IState escolheNave(int nave);

    IState comeca();

    IState moveNave(); // applyevent ou logo para orbita se for whormHole

    IState aplicaEvento();

    IState converter();

    IState converteRecurso(int x, int y);

    IState revive(int index);

    IState melhorarCargo();

    IState melhorarWeaponSys();

    IState reabastecerShields();

    IState compraDrone();

    IState compraShield();

    IState compraFuel();

    IState saiConverter();

    IState saiOrbita();

    IState entraPlaneta();

    IState emMovimento(char direcao);

    IState voltaInicio();

    IState acaba();

    IState saiPlaneta();
}
