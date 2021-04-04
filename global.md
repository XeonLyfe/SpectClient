<html>
  <head>
    <style>
      *{
        margin: 0px;
        padding: 0px;
      }
    body{
      background-color: #212121;
      margin-left: 400px;
      margin-top: 10px;
    }
    #container ul{
      list-style: none;
    }
    #container ul li{
      box-shadow: 0 0 10px 0 #000;
      background-color: #212121;
      width: 150px;
      border-bottom: 2px solid #0f0f0f;
      height: 50px;
      border-radius: 5px;
      line-height: 50px;
      text-align: center;
      float: left;
      color: white;
      font-size: 19px;
    }

    #container ul li:hover{
      background-color: #000;
    }

    #container ul ul{
      display: none;
    }

    #container ul li:hover > ul{
      display: block;
    }

    </style>
    <title> Dropdown Menu </title>
  </head>
  <body>
    <div id="container">
      <ul>
        <li>Player
          <ul>
            <li>Flight</li>
            <li>AutoSprint</li>
            <li>AutoArmor</li>
            <li>ChestStealer</li>
            <li>Glide</li>
            <li>Nuker</li>
            <li>AntiFall</li>
          </ul>
        <li>Visual
          <ul>
            <li>Glowing</li>
            <li>Trajectories</li>
            <li>XRay</li>
            <li>EntityESP</li>
            <li>ItemESP</li>
          </ul>
        <li>Combat
         <ul>
          <li>AntiBot</li>
          <li>AimBot</li>
          <li>BowAimBot</li>
          <li>Trigger</li>
          <li>Criticals</li>
          <li>KillAura</li>

        </ul>
        <li>Other
         <ul>
          <li>Targets</li>
          <li>Enemys</li>
          <li>Teams</li>
          <li>NoGuiEvents</li>
          <li>PluginsGetter</li>
          <li>AttackPacketCW</li>
          <li>AttackPacketCIA</li>
        </ul>
      </ul>
    </div>
  </body>
</html>
