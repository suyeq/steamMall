/*
Navicat MySQL Data Transfer

Source Server         : 47.107.69.19
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : steam

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-06-22 20:17:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL,
  `commentdate` datetime DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `gameid` bigint(40) DEFAULT NULL,
  `zannum` int(11) DEFAULT NULL,
  `cainum` int(11) DEFAULT NULL,
  `recommendstatu` int(11) DEFAULT NULL,
  `happy` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '苇名城的任何一个人都教过我死字怎么写', '2019-05-06 16:11:31', '12345678@qq.com', '10', '10', '0', '1', '0');
INSERT INTO `comment` VALUES ('2', '犹豫就会败北，好评就是白给', '2019-05-07 19:47:37', '12345678@qq.com', '10', '14', '0', '1', '0');
INSERT INTO `comment` VALUES ('3', '不说那么多，就直观感受\r\n难度肯定是有的，但不是那么夸张，实打实看见自己的操作在变强（特别是打苦难）\r\n初见杀什么的肯定有，但是恶意并没有那么多\r\n不管怎么样，游戏这个价这个质量，我觉得值了，后续dlc什么的，敲钟等着', '2019-05-02 19:49:57', '12345678@qq.com', '10', '17', '3', '1', '0');
INSERT INTO `comment` VALUES ('4', '每一个击败了苇名一心的人，都成为了自己心目中真正的剑圣。', '2019-05-03 19:51:16', '12345678@qq.com', '10', '9', '1', '1', '0');
INSERT INTO `comment` VALUES ('5', '难度太大了~~', '2019-05-04 19:51:48', '12345678@qq.com', '10', '6', '6', '0', '0');
INSERT INTO `comment` VALUES ('6', '痛并快乐，本体质量很棒，手残实在没办法_(:з)∠)_', '2019-05-06 19:52:45', '12345678@qq.com', '10', '7', '0', '1', '0');
INSERT INTO `comment` VALUES ('10', '只狼强无敌', '2019-06-04 17:43:40', '473721601@qq.com', '10', '0', '0', '1', '0');
INSERT INTO `comment` VALUES ('11', '好玩是好玩，就是手残党玩不起', '2019-06-04 17:45:47', '473721601@qq.com', '10', '0', '1', '0', null);
INSERT INTO `comment` VALUES ('12', '尼禄赛高~~~·', '2019-06-15 15:31:55', '473721601@qq.com', '16', '1', '0', '1', null);
INSERT INTO `comment` VALUES ('13', '啦啦啦啦啦，，，', '2019-06-15 17:23:05', '473721601@qq.com', '16', '6', '1', '0', '1');
INSERT INTO `comment` VALUES ('14', '方舟牛逼', '2019-06-15 21:47:06', '3162618364@qq.com', '20', '1', '0', '1', '0');
INSERT INTO `comment` VALUES ('15', '一个很好的galgame', '2019-06-19 11:11:34', '473721601@qq.com', '1', '1', '0', '1', '1');
INSERT INTO `comment` VALUES ('16', '很好的一款类魂游戏，在前作的基础上让得打击感更加的出色', '2019-06-19 14:48:06', '3162618364@qq.com', '10', '0', '0', '1', '0');

-- ----------------------------
-- Table structure for developer
-- ----------------------------
DROP TABLE IF EXISTS `developer`;
CREATE TABLE `developer` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of developer
-- ----------------------------
INSERT INTO `developer` VALUES ('1', 'Parasol');
INSERT INTO `developer` VALUES ('2', '\r\nEndnight Games Ltd');

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '游戏id',
  `gamename` varchar(255) DEFAULT NULL COMMENT '游戏名字',
  `gameintroduction` varchar(500) DEFAULT NULL COMMENT '游戏介绍',
  `gameabout` varchar(500) DEFAULT NULL COMMENT '关于游戏',
  `issuedstatu` int(11) DEFAULT NULL,
  `gameprice` int(11) DEFAULT NULL COMMENT '游戏价格',
  `issueddate` datetime DEFAULT NULL,
  `posterimage` bigint(40) DEFAULT NULL,
  `lowestsystem` bigint(40) DEFAULT NULL,
  `recommendsystem` bigint(40) DEFAULT NULL,
  `sellnum` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES ('1', '樱花片落恋模样 This is a very sweet love story.', '那是、非常甜蜜的青涩的恋爱模样 《樱花片落恋模样》是由日本知名游戏品牌Parasol于2017年发售的游戏。 由人气画师绘制的精美CG、经验丰富的团队制作的优秀配乐和出色的CV阵容， 能让你体验到与不同性格的女主角，独一无二的恋爱体验。', '自称平凡的普通学生的主人公：仁科春太。\r\n然而，他却过着“初恋女友其实是别离的亲妹”这种\r\n在古今中外的恋爱故事当中，早已用烂的设定的人生。\r\n\r\n就在这悲恋的伤口尚未痊愈的时候，迎来新春季节。\r\n春太所在的桑都学园中不断传出要被北条学园吸收合并的传言。\r\n\r\n去年，因为身为学园创设者的理事长突然去世，\r\n失去了至高领导的向心力，\r\n这所学园的经营状况每况愈下，现今状态已如风中残烛。\r\n\r\n而合并的另外一方北条学园，据说是依靠体罚控制学生，彻底地实施着成绩至上的教育方针。\r\n这样下去，以学生的自主、自立、自由为信条的桑都学园的理念将会消失……', '1', '136', '2019-04-09 19:13:58', '2', '1', '2', '90', '90');
INSERT INTO `game` VALUES ('2', 'The Forest.', 'As the lone survivor of a passenger jet crash, you find yourself in a mysterious forest battling to stay alive against a society of cannibalistic mutants. \r\n\r\nBuild, explore, survive in this terrifying first person survival horror simulator.', 'As the lone survivor of a passenger jet crash, you find yourself in a mysterious forest battling to stay alive against a society of cannibalistic mutants. \r\n\r\nBuild, explore, survive in this terrifying first person survival horror simulator.', '1', '70', '2019-03-06 19:47:01', '8', '3', '4', '0', '0');
INSERT INTO `game` VALUES ('3', 'BlazBlue: Chronophantasma Extend', '‘BLAZBLUE CHRONOPHANTASMA EXTEND’是 2D格斗和视觉小说结合的BLAZBLUE系列的第六个作品。包括2个新角色参战总共有28个角色可选择，还增加了丰富多彩的追加要素可称为...', '‘BLAZBLUE CHRONOPHANTASMA EXTEND’是 2D格斗和视觉小说结合的BLAZBLUE系列的第六个作品。包括2个新角色参战总共有28个角色可选择, 还增加了丰富多彩的追加要素可称为 CHRONOPHANTASMA系列的最高峰!', '1', '273', '2019-04-02 20:06:13', '14', '5', '6', '0', '90');
INSERT INTO `game` VALUES ('4', '隐形守护者', '《隐形守护者》长篇真人互动影像体验（橙光授权改编新作），创造属于每个人自己的谍战传奇！ 人间正道是沧桑！谨以此作品向曾经奋斗在秘密战线的无名英雄们致敬！', '总有一种理想，值得我们为之守护……你是否曾经幻想在抗战年代，独自潜伏于多方黑暗势力之中，成为一名隐形守护者，为抗战胜利奉献青春甚至牺牲生命？\r\n时代、信仰、忠孝、情义、爱恨、恩仇、人性……万象艰险之中，当你置身于两难境地之时，究竟该如何选择？乱世谍战，守护初心，看地下英雄如何周旋于各方势力！获取情报，扭转乾坤，你的选择诞生百千种生死剧情！踏上隐形的战场，这个使命你能否完成？这个秘密你能否守护？\r\n人间正道是沧桑，谨以此作品向曾经奋斗在秘密战线的无名英雄们致敬！', '1', '28', '2019-03-28 20:26:05', '20', '7', '8', '0', '88');
INSERT INTO `game` VALUES ('5', '绝地求生', '绝地求生(PLAYERUNKNOWN’S BATTLEGROUNDS)是战术竞技类型的游戏，每一局游戏将有100名玩家参与，他们将被投放在绝地岛(battlegrounds)的上空，游戏开始跳伞时所有人都一无所有。', '绝地求生(PLAYERUNKNOWN’S BATTLEGROUNDS)是战术竞技类游戏，每一局游戏将有100名玩家参与，他们将被投放在绝地岛(battlegrounds)的上空，游戏开始跳伞时所有人都一无所有。\r\n游戏展开的方式是：玩家赤手空拳地分布在岛屿的各个角落，利用岛上多样的武器与道具。\r\n随着时间的流逝，岛上的安全地带越来越少，特定地区也会发生爆炸的情况，最终只有一人存活获得胜利。', '1', '98', '2019-04-22 20:36:24', '26', '9', '10', '1', '88');
INSERT INTO `game` VALUES ('6', 'Anno 1800', '在《Anno 1800》里引领工业革命！ 体验史上瞬息万变的精彩时期之一。发现新科技、新大陆和新社会。', '在《Anno 1800》里引领工业革命！\r\n\r\n体验史上瞬息万变的精彩时期之一。发现新科技、新大陆和新社会。打造你梦想中的新世界！收集到的所有原料都是为了创造值得回味的《Anno》游戏体验。游历工业革命时期的世界并写下你的故事！', '1', '220', '2019-04-09 21:27:21', '32', '11', '12', '79', '79');
INSERT INTO `game` VALUES ('7', 'Supraland', '混合了传送门、萨尔达和银河战士。探索、寻找隐藏的升级道具，解开谜题、击败怪兽或是寻找新的能力帮助你到达新的地区。Supraland 是一款第一人称的解谜型类银河战士恶魔城游戏。游戏的灵感主要来自於萨尔达、银河战士和传送门。', 'Supraland 是一款第一人称的解谜型类银河战士恶魔城游戏。游戏的灵感主要来自於萨尔达、银河战士和传送门。\r\nSupraland 不用繁琐的对话、多余的教学干扰你的游玩体验，以简单的小故事给予一个明确的首要目标，剩下的由聪明的你自行探索、发掘。', '1', '19', '2019-04-23 21:41:25', '38', '13', '14', '70', '70');
INSERT INTO `game` VALUES ('8', 'Grand Theft Auto V', '洛圣都是一座有着明亮灯光、漫长夜晚和肮脏秘密的城市，其最亮、最长、最脏的一面，尽在 GTA 在线模式：不夜城。派对现在开始。', '与传奇经理人托尼·普林斯合伙经营顶级夜总会，请来世界闻名的 DJ 索洛蒙、我们的故事、迪克森以及黑色圣母，将它打造成圣安地列斯有史以来最集中的犯罪集团网的门面。\r\n\r\n一个初涉江湖的街头新丁、一个洗手多年的银行劫匪和一个丧心病狂的杀人狂魔，误打误撞中深陷犯罪集团、美国政府和娱乐产业之间盘根错杂的恐怖困境。他们必须齐心协力，接连完成九死一生的惊天劫案，才能在这个冷血无情的城市中苟延残喘。不要相信任何人，尤其是你的同伙！ ', '1', '290', '2019-04-01 21:56:44', '44', '15', '16', '0', '0');
INSERT INTO `game` VALUES ('9', 'Human Fall Flat', '人类一败涂地是一款与众不同的基于物理特性的开放结局解谜探索游戏。游戏发生于若干飘浮在云中的幻境中。你的目标就是用你仅有的智慧和物理原理解开谜题，从这些荒诞梦境中逃出来。', '人类一败涂地的世界拥有先进的物理引擎和创新的操作，能够为您带来多种挑战。Bob的坠落之梦中夹杂了许多需要解决的谜题和让你忘记本来目的的小玩意儿，贸然尝试它们可能会闹出不少笑料。这些世界可能看上去很奇幻，但物理法则可是很真实的。\r\n\r\n你会想打开那扇神秘之门吗？还是说你只想看看你能把一套音响扔多远？', '1', '21', '2019-04-09 22:22:41', '50', '17', '18', '0', '80');
INSERT INTO `game` VALUES ('10', 'Sekiro™: Shadows Die Twice', '进入由打造了《黑暗之魂》系列的知名开发商FromSoftware倾力制作的全新冒险，用智慧和力量斩开复仇之路。 决死复仇，夺回荣誉，智杀强敌。', '在《Sekiro: Shadows Die Twice》中你是“独臂之狼”，一个名誉不再、伤痕累累的忍者，一个从死亡边缘捡回一命的战士。你效忠守护继承古老血统的年轻皇子，与危险的苇名一族以及众多凶恶之徒为敌。年轻的皇子被抓走后，为挽回荣誉，你将不畏死亡，踏上危机四伏的征程。\r\n\r\n探索生死冲突不断的16世纪后期，感受残酷的日本战国时代，在黑暗、扭曲的世界，与威胁生命的敌人对峙。活用义手装备各种致命武器，大显忍者身手，在血腥对抗中潜行、上下穿梭，与敌人正面激烈交锋。\r\n\r\n复仇雪耻。夺回荣誉。巧妙杀敌。', '1', '269', '2019-04-06 22:44:34', '56', '19', '20', '1', '68');
INSERT INTO `game` VALUES ('11', 'Train Valley 2', '在我们的列车大亨益智游戏中推动工业革命向前发展！建造一条高效的铁路，升级机车，并让所有列车在轨道上运行，以满足你的小山谷中城市和工业不断增长的需求。', 'Train Valley 2 是一款列车大亨益智游戏。将你的铁路公司从工业革命时代带到未来，并满足山谷中城市和工业的需求。建造铁路，升级机车，让列车按照时刻表运行，不发生任何延误或事故。全体上车 — 列车即将驶离车站！', '1', '30', '2019-02-21 22:58:57', '62', '21', '22', '0', '70');
INSERT INTO `game` VALUES ('12', 'Shadow of the Tomb Raider', '劳拉·克劳馥一路狂奔，拯救世界免遭玛雅预言中的天灾摧毁，她终将迎接命运，成长为命中注定的古墓侠盗。', '一同见证劳拉·克劳馥在古墓里的丽影英姿。《Shadow of the Tomb Raider》游戏中，劳拉必须征服致命的丛林，穿越恐怖的古墓，撑过自己人生中最黑暗的时刻。劳拉一路狂奔，拯救世界免遭玛雅预言中的天灾摧毁，她终将迎接命运，成长为命中注定的古墓侠盗。\r\n', '0', '220', '2019-04-27 23:11:12', '68', '23', '24', '0', '0');
INSERT INTO `game` VALUES ('13', 'Risk of Rain 2', '经典的地牢探索类多人游戏《雨中冒险》以3D版的形式，带着更具挑战性的动作回归了。您可以独自玩，或者与至多四名好友组队，一路过关斩将，与大量的怪物作战，解锁新的战利品，并最终找到一条路逃离这颗星球', '经典的地牢探索类多人游戏《雨中冒险》以3D版的形式，带着更具挑战性的动作回归了。在随机关卡、敌人、Boss和道具的共同作用下，每一次的冒险历程都是截然不同的。您可以独自玩，或者与至多四名好友组队，一路过关斩将，与大量的怪物作战，解锁新的战利品，并最终找到一条路逃离这颗星球。每进行一次冒险，您都会对敌人的模式更加了解，继而用纯熟的技巧来克服哪怕最困难的情况。独特的等级调整系统意味着您和您的敌人都将在游戏过程中无限增加力量——之前的Boss可能会在后面变成普通的敌人。', '1', '70', '2019-03-25 21:26:50', '74', '23', '24', '88', '88');
INSERT INTO `game` VALUES ('14', 'MONSTER HUNTER: WORLD', '新的生命之地。狩猎, 就是本能! 在系列最新作品「Monster Hunter: World」中,玩家可以体验终极的狩猎生活,活用新建构的世界中各种各样的地形与生态环境享受狩猎的惊喜与兴奋。', '在壮丽的大自然中与魔物进行一场史诗式的决斗。\r\n\r\n玩家化身成猎人, 接受任务狩猎生活栖息在各种环境中的魔物。利用狩猎魔物取得的材料, 制作更强的武器和防具, 挑战更强大的魔物。\r\n\r\n在系列最新作品「Monster Hunter: World」中,玩家可以体验终极的狩猎生活,活用新建构的世界中各种各样的地形与生态环境享受狩猎的惊喜与兴奋。', '1', '278', '2018-12-19 21:39:51', '80', '19', '20', '70', '70');
INSERT INTO `game` VALUES ('15', 'RESIDENT EVIL 2 / BIOHAZARD RE:2', '一切都凌驾于玩家的想像之上。 一场生化灾难於1998年9月袭击了浣熊市，从残害幸存者的丧尸地狱中生还吧。 无尽的刺激、引人入胜的故事和无法想像的恐怖正在等候你。 见证系列首屈一指的杰作──《Resident Evil 2》的重生吧。 別被这场惨剧吞噬。', '生存恐怖游戏代表之作Resident Evil 2回归，带来重新建构的深层体验。通过Capcom专利的游戏引擎RE Engine，经典作品经过翻新，在原来的游戏模式之上，添加了惊艳的写实视觉效果，让人仿佛身临其境的音效，全新的越肩视角和操作方式。\r\n\r\n本作中，Resident Evil系列经典的动作、紧凑的探索和解谜要素得以回归。玩家将会扮演新人警察里昂·S·肯尼迪和大学生克莱尔·雷德菲尔德，应对在浣熊市爆发的僵尸危机。里昂和克莱尔各有自己的路线，使玩家可从两人的不同角度欣赏剧情。玩家将掌控这两位极具人气的主人公的命运，分别操作他们揭开生化攻击背后的黑幕并最终成功逃脱危机。\r\n', '1', '208', '2018-12-18 21:46:01', '86', '19', '20', '90', '90');
INSERT INTO `game` VALUES ('16', 'Fate/EXTELLA LINK', '命运/异章系列最新作品《命运/创世 连接》于STEAM上架。 在《命运》系列中登场的从者跨作参战。在这部动作游戏中，你可以尽情驰骋疆场，发动宝具等华丽招式展开战斗。', '灵子虚构世界“SE.RA.PH”的战火已平息， 在争夺月之圣杯的战斗中重获新生的阿提拉和御主一起在领地散步时， 遇上了一群突然出现的攻性程序。 和平已经到来，攻性程序本不该再出现。就在遭到攻击的御主一行感到疑惑不解时， 素未谋面的从者——查理曼出现在眼前。 “这种时候就是要飒爽斩敌才够帅气啊！” 在查理曼的帮助下，御主渡过了危机， 但本该在身旁的年幼阿提拉却不知所踪——', '1', '168', '2019-05-27 11:46:46', '92', '19', '20', '0', '0');
INSERT INTO `game` VALUES ('17', 'DARK SOULS™ III', 'Dark Souls即将推出极具话题性及代表性的系列新作。', '当火渐熄，世界趋于毁灭，您将再踏上面临更多磨难、大量敌人与难关的旅途。玩家将沉浸在史诗氛围的世界之中，感受更快速的游玩节奏与棘手的战斗强度带来的黑暗气息。不论新手或是老玩家，都将因著名的游戏体验、实境般的游戏画面为之着迷。 如今仅剩余火尚存……作好准备，再次拥抱黑暗！', '1', '256', '2019-05-03 11:55:39', '98', '19', '20', '98', '68');
INSERT INTO `game` VALUES ('18', 'FINAL FANTASY XV WINDOWS EDITION', '展开一场超凡绝伦的高质量旅程吧！本游戏提供各种丰富额外内容，而且支持超高分辨率的图形选项和 HDR 10，立即来畅玩这款画面精美且精心制作的《FINAL FANTASY XV》，享受前所未有的游戏体验。', '与挚友一同踏上这场永生難忘的旅程，穿越令人屏息的开放世界、目睹迷人的大地风采，在旅途中挑战体型惊人的猛兽，并奋力打败强大敌人，夺回家园。 采用动感十足的战斗系统，在惊险刺激的战斗中，轻松引导先祖的力量，穿梭战场时空。和战友携手合作，掌握武器、魔法和团队战斗的技能。', '1', '340', '2019-05-06 12:02:43', '104', '19', '20', '98', '45');
INSERT INTO `game` VALUES ('19', 'Sword Art Online: Fatal Bullet', 'SAO游戏最新作品是「您」化身为主角的TPSRPG。在枪械与钢铁的世界──《Gun Gale Online》中，作为桐人他们的战友出生入死，并以英雄为目标向前迈进。依照自己喜好成长虚拟角色，并灵活运用多样化的武器，在您专属的战场中幸存下来吧。', '这般抉择，比子弹更凶猛、更沉重。 SAO游戏最新作品是「您」化身为主角的RPG。在TPS（第三人称射击游戏）风格的战斗中，增加了成长性、故事性等RPG的快感，SAO将以TPSRPG类型游戏脱胎换骨。本游戏舞台为枪械与钢铁的世界──《Gun Gale Online》。以新人玩家身份登入《GGO》的「您」，偶然获得稀有支持AI《阿尔法系统》作为搭档，逐步迈向成为英雄的道路。依照自己喜好成长作为自己分身的虚拟角色，并灵活运用多样化的武器与技能，在您专属的战场中挺过枪林弹雨吧。本游戏也有使用已获得武器与培养角色和其他玩家对战、共战的在线多人游玩。', '1', '147', '2019-04-29 12:10:41', '110', '19', '20', '99', '0');
INSERT INTO `game` VALUES ('20', 'ARK: Survival Evolved', '由虚幻4引擎打造的一款多人在线生存竞技网游，在一个超高自由度的开放世界里，可以体验采集、制造、打猎、收获、建造、研究以及驯服恐龙等超多自由内容，感受酷热白昼、冰冷夜晚的动态天气系统以及饥饿口渴等现实中的生存挑战，还要面对其它生存者的威胁，合作生存还是竞技厮杀，由你决定！', '孤身一人苏醒在空旷的海岸边，发现自己身处一个充满恐龙的神秘岛屿“ARK”，你身无寸缕，饥寒交迫，你必须学会打猎、获取资源，制作物品，种植庄稼，发展科技，建立庇护所，从而让你可以在这个世界中存活下去。同时，你也可以利用技巧杀死或者驯服、繁殖、骑乘那些生活在这片岛屿上的洪荒巨兽。你也可以与上百人建成部落，互相侵略，生存...以及逃离这片孤岛！', '1', '135', '2019-05-29 12:15:27', '116', '19', '20', '101', '0');

-- ----------------------------
-- Table structure for game_developer
-- ----------------------------
DROP TABLE IF EXISTS `game_developer`;
CREATE TABLE `game_developer` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `gameid` bigint(40) DEFAULT NULL,
  `developerid` bigint(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_developer
-- ----------------------------
INSERT INTO `game_developer` VALUES ('1', '1', '1');
INSERT INTO `game_developer` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for game_image
-- ----------------------------
DROP TABLE IF EXISTS `game_image`;
CREATE TABLE `game_image` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `gameid` bigint(40) DEFAULT NULL,
  `image1` bigint(40) DEFAULT NULL,
  `image2` bigint(40) DEFAULT NULL,
  `image3` bigint(40) DEFAULT NULL,
  `image4` bigint(40) DEFAULT NULL,
  `image5` bigint(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_image
-- ----------------------------
INSERT INTO `game_image` VALUES ('1', '1', '3', '4', '5', '6', '7');
INSERT INTO `game_image` VALUES ('2', '2', '9', '10', '11', '12', '13');
INSERT INTO `game_image` VALUES ('3', '3', '15', '16', '17', '18', '19');
INSERT INTO `game_image` VALUES ('4', '4', '21', '22', '23', '24', '25');
INSERT INTO `game_image` VALUES ('5', '5', '27', '28', '29', '30', '31');
INSERT INTO `game_image` VALUES ('6', '6', '33', '34', '35', '36', '37');
INSERT INTO `game_image` VALUES ('7', '7', '39', '40', '41', '42', '43');
INSERT INTO `game_image` VALUES ('8', '8', '45', '46', '47', '48', '49');
INSERT INTO `game_image` VALUES ('9', '9', '51', '52', '53', '54', '55');
INSERT INTO `game_image` VALUES ('10', '10', '57', '58', '59', '60', '61');
INSERT INTO `game_image` VALUES ('11', '11', '63', '64', '65', '66', '67');
INSERT INTO `game_image` VALUES ('12', '12', '69', '70', '71', '72', '73');
INSERT INTO `game_image` VALUES ('13', '13', '75', '76', '77', '78', '79');
INSERT INTO `game_image` VALUES ('14', '14', '81', '82', '83', '84', '85');
INSERT INTO `game_image` VALUES ('15', '15', '87', '88', '89', '90', '91');
INSERT INTO `game_image` VALUES ('16', '16', '93', '94', '95', '96', '97');
INSERT INTO `game_image` VALUES ('17', '17', '99', '100', '101', '102', '103');
INSERT INTO `game_image` VALUES ('18', '18', '105', '106', '107', '108', '109');
INSERT INTO `game_image` VALUES ('19', '19', '111', '112', '113', '114', '115');
INSERT INTO `game_image` VALUES ('20', '20', '117', '118', '119', '120', '121');

-- ----------------------------
-- Table structure for game_label
-- ----------------------------
DROP TABLE IF EXISTS `game_label`;
CREATE TABLE `game_label` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `gameid` bigint(40) DEFAULT NULL,
  `labelid` bigint(40) DEFAULT NULL,
  `hotnum` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_label
-- ----------------------------
INSERT INTO `game_label` VALUES ('1', '1', '1', '0');
INSERT INTO `game_label` VALUES ('2', '1', '2', '0');
INSERT INTO `game_label` VALUES ('3', '1', '3', '0');
INSERT INTO `game_label` VALUES ('4', '1', '4', '2');
INSERT INTO `game_label` VALUES ('5', '2', '2', '0');
INSERT INTO `game_label` VALUES ('6', '2', '5', '0');
INSERT INTO `game_label` VALUES ('7', '2', '6', '0');
INSERT INTO `game_label` VALUES ('8', '2', '7', '0');
INSERT INTO `game_label` VALUES ('9', '2', '8', '0');
INSERT INTO `game_label` VALUES ('10', '2', '9', '0');
INSERT INTO `game_label` VALUES ('11', '3', '3', '0');
INSERT INTO `game_label` VALUES ('12', '3', '11', '0');
INSERT INTO `game_label` VALUES ('13', '3', '10', '0');
INSERT INTO `game_label` VALUES ('14', '4', '1', '0');
INSERT INTO `game_label` VALUES ('15', '4', '2', '0');
INSERT INTO `game_label` VALUES ('16', '4', '12', '1');
INSERT INTO `game_label` VALUES ('17', '4', '13', '2');
INSERT INTO `game_label` VALUES ('18', '5', '2', '0');
INSERT INTO `game_label` VALUES ('19', '5', '4', '0');
INSERT INTO `game_label` VALUES ('20', '5', '14', '0');
INSERT INTO `game_label` VALUES ('21', '5', '15', '0');
INSERT INTO `game_label` VALUES ('22', '6', '6', '0');
INSERT INTO `game_label` VALUES ('23', '6', '8', '0');
INSERT INTO `game_label` VALUES ('24', '6', '16', '0');
INSERT INTO `game_label` VALUES ('25', '6', '17', '0');
INSERT INTO `game_label` VALUES ('26', '6', '18', '0');
INSERT INTO `game_label` VALUES ('27', '7', '2', '0');
INSERT INTO `game_label` VALUES ('28', '7', '16', '0');
INSERT INTO `game_label` VALUES ('29', '7', '17', '0');
INSERT INTO `game_label` VALUES ('30', '7', '19', '0');
INSERT INTO `game_label` VALUES ('31', '7', '20', '0');
INSERT INTO `game_label` VALUES ('32', '8', '6', '0');
INSERT INTO `game_label` VALUES ('33', '8', '16', '0');
INSERT INTO `game_label` VALUES ('34', '8', '17', '0');
INSERT INTO `game_label` VALUES ('35', '8', '19', '0');
INSERT INTO `game_label` VALUES ('36', '8', '20', '0');
INSERT INTO `game_label` VALUES ('37', '8', '21', '0');
INSERT INTO `game_label` VALUES ('38', '9', '15', '0');
INSERT INTO `game_label` VALUES ('39', '9', '22', '0');
INSERT INTO `game_label` VALUES ('40', '9', '23', '0');
INSERT INTO `game_label` VALUES ('41', '9', '24', '0');
INSERT INTO `game_label` VALUES ('42', '10', '17', '2');
INSERT INTO `game_label` VALUES ('43', '10', '25', '1');
INSERT INTO `game_label` VALUES ('44', '10', '22', '1');
INSERT INTO `game_label` VALUES ('45', '10', '27', '2');
INSERT INTO `game_label` VALUES ('46', '10', '26', '2');
INSERT INTO `game_label` VALUES ('47', '11', '9', '0');
INSERT INTO `game_label` VALUES ('48', '11', '19', '0');
INSERT INTO `game_label` VALUES ('49', '11', '28', '0');
INSERT INTO `game_label` VALUES ('50', '11', '29', '0');
INSERT INTO `game_label` VALUES ('51', '12', '2', '0');
INSERT INTO `game_label` VALUES ('52', '12', '6', '0');
INSERT INTO `game_label` VALUES ('53', '12', '10', '0');
INSERT INTO `game_label` VALUES ('54', '12', '25', '0');
INSERT INTO `game_label` VALUES ('55', '13', '15', '0');
INSERT INTO `game_label` VALUES ('56', '13', '22', '0');
INSERT INTO `game_label` VALUES ('57', '13', '23', '0');
INSERT INTO `game_label` VALUES ('58', '14', '2', '0');
INSERT INTO `game_label` VALUES ('59', '14', '6', '0');
INSERT INTO `game_label` VALUES ('60', '14', '15', '0');
INSERT INTO `game_label` VALUES ('61', '14', '23', '0');
INSERT INTO `game_label` VALUES ('62', '15', '7', '0');
INSERT INTO `game_label` VALUES ('63', '15', '10', '0');
INSERT INTO `game_label` VALUES ('64', '15', '13', '0');
INSERT INTO `game_label` VALUES ('65', '15', '30', '0');
INSERT INTO `game_label` VALUES ('66', '16', '3', '0');
INSERT INTO `game_label` VALUES ('67', '16', '10', '0');
INSERT INTO `game_label` VALUES ('68', '16', '15', '0');
INSERT INTO `game_label` VALUES ('69', '17', '6', '0');
INSERT INTO `game_label` VALUES ('70', '17', '12', '0');
INSERT INTO `game_label` VALUES ('71', '17', '13', '0');
INSERT INTO `game_label` VALUES ('72', '17', '17', '0');
INSERT INTO `game_label` VALUES ('73', '18', '6', '0');
INSERT INTO `game_label` VALUES ('74', '18', '12', '0');
INSERT INTO `game_label` VALUES ('75', '18', '13', '1');
INSERT INTO `game_label` VALUES ('76', '19', '6', '0');
INSERT INTO `game_label` VALUES ('77', '19', '12', '0');
INSERT INTO `game_label` VALUES ('78', '19', '3', '0');
INSERT INTO `game_label` VALUES ('79', '20', '6', '0');
INSERT INTO `game_label` VALUES ('80', '20', '12', '0');
INSERT INTO `game_label` VALUES ('81', '20', '13', '0');
INSERT INTO `game_label` VALUES ('84', '10', '32', '0');
INSERT INTO `game_label` VALUES ('85', '10', '33', '1');
INSERT INTO `game_label` VALUES ('86', '10', '34', '2');
INSERT INTO `game_label` VALUES ('87', '4', '35', '0');
INSERT INTO `game_label` VALUES ('88', '1', '36', '0');

-- ----------------------------
-- Table structure for game_language
-- ----------------------------
DROP TABLE IF EXISTS `game_language`;
CREATE TABLE `game_language` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `languageid` bigint(40) DEFAULT NULL,
  `gameid` bigint(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_language
-- ----------------------------

-- ----------------------------
-- Table structure for game_platform
-- ----------------------------
DROP TABLE IF EXISTS `game_platform`;
CREATE TABLE `game_platform` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `gameid` bigint(40) DEFAULT NULL,
  `platformid` bigint(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_platform
-- ----------------------------

-- ----------------------------
-- Table structure for game_publisher
-- ----------------------------
DROP TABLE IF EXISTS `game_publisher`;
CREATE TABLE `game_publisher` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `gameid` bigint(40) DEFAULT NULL,
  `publisherid` bigint(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_publisher
-- ----------------------------
INSERT INTO `game_publisher` VALUES ('1', '1', '1');
INSERT INTO `game_publisher` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for game_type
-- ----------------------------
DROP TABLE IF EXISTS `game_type`;
CREATE TABLE `game_type` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `gameid` bigint(40) DEFAULT NULL,
  `typeid` bigint(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_type
-- ----------------------------
INSERT INTO `game_type` VALUES ('1', '1', '2');
INSERT INTO `game_type` VALUES ('2', '2', '1');
INSERT INTO `game_type` VALUES ('3', '2', '2');
INSERT INTO `game_type` VALUES ('4', '2', '3');
INSERT INTO `game_type` VALUES ('5', '2', '4');
INSERT INTO `game_type` VALUES ('6', '3', '5');
INSERT INTO `game_type` VALUES ('7', '4', '2');
INSERT INTO `game_type` VALUES ('8', '4', '3');
INSERT INTO `game_type` VALUES ('9', '4', '6');
INSERT INTO `game_type` VALUES ('10', '5', '1');
INSERT INTO `game_type` VALUES ('11', '5', '2');
INSERT INTO `game_type` VALUES ('12', '5', '7');
INSERT INTO `game_type` VALUES ('13', '6', '4');
INSERT INTO `game_type` VALUES ('14', '6', '8');
INSERT INTO `game_type` VALUES ('15', '7', '1');
INSERT INTO `game_type` VALUES ('16', '7', '2');
INSERT INTO `game_type` VALUES ('17', '7', '3');
INSERT INTO `game_type` VALUES ('18', '8', '1');
INSERT INTO `game_type` VALUES ('19', '8', '2');
INSERT INTO `game_type` VALUES ('20', '9', '2');
INSERT INTO `game_type` VALUES ('21', '9', '3');
INSERT INTO `game_type` VALUES ('22', '10', '1');
INSERT INTO `game_type` VALUES ('23', '10', '2');
INSERT INTO `game_type` VALUES ('24', '11', '3');
INSERT INTO `game_type` VALUES ('25', '11', '4');
INSERT INTO `game_type` VALUES ('26', '11', '8');
INSERT INTO `game_type` VALUES ('27', '11', '9');
INSERT INTO `game_type` VALUES ('28', '12', '1');
INSERT INTO `game_type` VALUES ('29', '12', '2');
INSERT INTO `game_type` VALUES ('30', '13', '1');
INSERT INTO `game_type` VALUES ('31', '13', '3');
INSERT INTO `game_type` VALUES ('33', '14', '1');
INSERT INTO `game_type` VALUES ('34', '15', '1');
INSERT INTO `game_type` VALUES ('35', '16', '1');
INSERT INTO `game_type` VALUES ('36', '17', '1');
INSERT INTO `game_type` VALUES ('37', '18', '1');
INSERT INTO `game_type` VALUES ('38', '19', '1');
INSERT INTO `game_type` VALUES ('39', '20', '1');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `gamename` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('1', 'https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/95/959986934ff7a3782a0746a96d5f5df8524d9c2b_full.jpg', 'avatar', 'avatar');
INSERT INTO `image` VALUES ('2', 'https://media.st.dl.bscstorage.net/steam/apps/873290/capsule_616x353_schinese.jpg?t=1549352808', '樱花片落恋模样 This is a very sweet love story.', 'post');
INSERT INTO `image` VALUES ('3', 'https://media.st.dl.bscstorage.net/steam/apps/873290/ss_9e1bf936fbe8101685db41c45130accc269f6216.600x338.jpg', '樱花片落恋模样 This is a very sweet love story.', 'intro');
INSERT INTO `image` VALUES ('4', 'https://media.st.dl.bscstorage.net/steam/apps/873290/ss_91758003f640024d8e5efdfbaf1e35a391171e8b.600x338.jpg', '樱花片落恋模样 This is a very sweet love story.', 'intro');
INSERT INTO `image` VALUES ('5', 'https://media.st.dl.bscstorage.net/steam/apps/873290/ss_74a53c1ce8c79d4ea565f0b8e51a75fb3332742a.600x338.jpg', '樱花片落恋模样 This is a very sweet love story.', 'intro');
INSERT INTO `image` VALUES ('6', 'https://media.st.dl.bscstorage.net/steam/apps/873290/ss_ab122d3b882e6842ed370a0b832072b4e0fdedd5.600x338.jpg', '樱花片落恋模样 This is a very sweet love story.', 'intro');
INSERT INTO `image` VALUES ('7', 'https://media.st.dl.bscstorage.net/steam/apps/873290/ss_35612a8a8101744e29adeae6f3e11c53beaa7ae9.600x338.jpg', '樱花片落恋模样 This is a very sweet love story.', 'intro');
INSERT INTO `image` VALUES ('8', 'https://media.st.dl.bscstorage.net/steam/apps/242760/capsule_616x353.jpg?t=1527008565', 'The Forest', 'post');
INSERT INTO `image` VALUES ('9', 'https://media.st.dl.bscstorage.net/steam/apps/242760/ss_d03a261fecab226a0ecac5746225c2a50d65c670.600x338.jpg', 'The Forest', 'intro');
INSERT INTO `image` VALUES ('10', 'https://media.st.dl.bscstorage.net/steam/apps/242760/ss_8ccb821c4df3fafdf4161d77f38635441a8157f2.600x338.jpg', 'The Forest', 'intro');
INSERT INTO `image` VALUES ('11', 'https://media.st.dl.bscstorage.net/steam/apps/242760/ss_a37e6873baf869be91010b20c30a7e61e4b82cc1.600x338.jpg', 'The Forest', 'intro');
INSERT INTO `image` VALUES ('12', 'https://media.st.dl.bscstorage.net/steam/apps/242760/ss_7598551a5bfbf69ae1161c8ebee8e000868add24.600x338.jpg', 'The Forest', 'intro');
INSERT INTO `image` VALUES ('13', 'https://media.st.dl.bscstorage.net/steam/apps/242760/ss_d77d402c78451a04b5c370e81ff7767c4008343c.600x338.jpg?t=1527008565', 'The Forest', 'intro');
INSERT INTO `image` VALUES ('14', 'https://media.st.dl.bscstorage.net/steam/apps/388750/capsule_616x353.jpg?t=1457661631', 'BlazBlue: Chronophantasma Extend', 'post');
INSERT INTO `image` VALUES ('15', 'https://media.st.dl.bscstorage.net/steam/apps/388750/ss_bfa8003bf4b7b59503de695f18b2b6b37eb72d06.600x338.jpg', 'BlazBlue: Chronophantasma Extend', 'intro');
INSERT INTO `image` VALUES ('16', 'https://media.st.dl.bscstorage.net/steam/apps/388750/ss_d19ec88cf6a75cffcbd659afa9c0ffeef854c844.600x338.jpg', 'BlazBlue: Chronophantasma Extend', 'intro');
INSERT INTO `image` VALUES ('17', 'https://media.st.dl.bscstorage.net/steam/apps/388750/ss_9472ed24a3d671b02bfed11fbfa90ea4d7bac9bb.600x338.jpg', 'BlazBlue: Chronophantasma Extend', 'intro');
INSERT INTO `image` VALUES ('18', 'https://media.st.dl.bscstorage.net/steam/apps/388750/ss_bfb96daf1cf51ca74684f03bd9d31e03d348f978.600x338.jpg', 'BlazBlue: Chronophantasma Extend', 'intro');
INSERT INTO `image` VALUES ('19', 'https://media.st.dl.bscstorage.net/steam/apps/388750/ss_d024e349eba0c190287b18c78a77e37128ff596f.600x338.jpg?t=1457661631', 'BlazBlue: Chronophantasma Extend', 'intro');
INSERT INTO `image` VALUES ('20', 'https://media.st.dl.bscstorage.net/steam/bundles/9867/y6nqoo26n2qe0bnq/capsule_616x353.jpg?t=1553500328', '隐形守护者', 'post');
INSERT INTO `image` VALUES ('21', 'https://media.st.dl.bscstorage.net/steam/apps/998940/ss_27f80c55518e7dfd08ee3ac3f3dbf41719bff7fe.600x338.jpg', '隐形守护者', 'intro');
INSERT INTO `image` VALUES ('22', 'https://media.st.dl.bscstorage.net/steam/apps/1012220/ss_c60fafe7d34c68ff4be0fdd90dd479aaaa9ded75.600x338.jpg', '隐形守护者', 'intro');
INSERT INTO `image` VALUES ('23', 'https://media.st.dl.bscstorage.net/steam/apps/998940/ss_9c1b4bf486edccbf71a5ae6939577fb0abb492ab.600x338.jpg', '隐形守护者', 'intro');
INSERT INTO `image` VALUES ('24', 'https://media.st.dl.bscstorage.net/steam/apps/1012220/ss_1f3fc015d50731a1bf92c3b8fc56ddf34179cc25.600x338.jpg', '隐形守护者', 'intro');
INSERT INTO `image` VALUES ('25', 'https://media.st.dl.bscstorage.net/steam/apps/998940/ss_0ba2ecf3a5d8eabd5f14e2fcb72c2cbb1285cca4.600x338.jpg?t=1555936279', '隐形守护者', 'intro');
INSERT INTO `image` VALUES ('26', 'https://media.st.dl.bscstorage.net/steam/apps/578080/capsule_616x353.jpg?t=1554496855', '绝地求生', 'post');
INSERT INTO `image` VALUES ('27', 'https://media.st.dl.bscstorage.net/steam/apps/578080/ss_e7e79847eff0933de92192bb62b8bc7068d611da.600x338.jpg', '绝地求生', 'intro');
INSERT INTO `image` VALUES ('28', 'https://media.st.dl.bscstorage.net/steam/apps/578080/ss_a3f3f8894f4a4eb4d17d7e41c8e1f195f37ba896.600x338.jpg', '绝地求生', 'intro');
INSERT INTO `image` VALUES ('29', 'https://media.st.dl.bscstorage.net/steam/apps/578080/ss_2c79b3b590b186b10bf082d37674621f204a3497.600x338.jpg', '绝地求生', 'intro');
INSERT INTO `image` VALUES ('30', 'https://media.st.dl.bscstorage.net/steam/apps/578080/ss_23af2e59855a833c22d0c11ca23a719f54a554ff.600x338.jpg', '绝地求生', 'intro');
INSERT INTO `image` VALUES ('31', 'https://media.st.dl.bscstorage.net/steam/apps/578080/ss_2aa1de6f978ae9eb6dbe8fcf061395677c0460ab.600x338.jpg?t=1556089957', '绝地求生', 'intro');
INSERT INTO `image` VALUES ('32', 'https://media.st.dl.bscstorage.net/steam/apps/916440/capsule_616x353.jpg?t=1555079345', 'Anno 1800', 'post');
INSERT INTO `image` VALUES ('33', 'https://media.st.dl.bscstorage.net/steam/apps/916440/ss_c9ceb8a8c4d493fe728892d1c69fbd2ed36f6869.600x338.jpg', 'Anno 1800', 'intro');
INSERT INTO `image` VALUES ('34', 'https://media.st.dl.bscstorage.net/steam/apps/916440/ss_ccbe726acab1604f3d560a8ff16aa6abe80eea4c.600x338.jpg', 'Anno 1800', 'intro');
INSERT INTO `image` VALUES ('35', 'https://media.st.dl.bscstorage.net/steam/apps/916440/ss_b93fc4892e88b96cbdc18f0568f7e3807e23eb63.600x338.jpg', 'Anno 1800', 'intro');
INSERT INTO `image` VALUES ('36', 'https://media.st.dl.bscstorage.net/steam/apps/916440/ss_488e9af47c7e76b07918da2bcb3d5eb061b5f3db.600x338.jpg', 'Anno 1800', 'intro');
INSERT INTO `image` VALUES ('37', 'https://media.st.dl.bscstorage.net/steam/apps/916440/ss_76c08b5b16abd130af417b980bf12fed4153926b.600x338.jpg?t=1555430587', 'Anno 1800', 'intro');
INSERT INTO `image` VALUES ('38', 'https://media.st.dl.bscstorage.net/steam/apps/813630/capsule_616x353.jpg?t=1555201253', 'Supraland', 'post');
INSERT INTO `image` VALUES ('39', 'https://media.st.dl.bscstorage.net/steam/apps/813630/ss_f2973b56bcd8786c8643163179f3fd155836448a.600x338.jpg', 'Supraland', 'intro');
INSERT INTO `image` VALUES ('40', 'https://media.st.dl.bscstorage.net/steam/apps/813630/ss_2595c90ee1a524d1f4a21f4012bfcae4af544945.600x338.jpg', 'Supraland', 'intro');
INSERT INTO `image` VALUES ('41', 'https://media.st.dl.bscstorage.net/steam/apps/813630/ss_8a01de634e5470eb32db61772870a91693f576e2.600x338.jpg', 'Supraland', 'intro');
INSERT INTO `image` VALUES ('42', 'https://media.st.dl.bscstorage.net/steam/apps/813630/ss_2196cce530d054f647b35d4603cf89cc1f562f6a.600x338.jpg', 'Supraland', 'intro');
INSERT INTO `image` VALUES ('43', 'https://media.st.dl.bscstorage.net/steam/apps/813630/ss_0b56bbe089dd78be78478c82c01adb56fc2d0d7d.600x338.jpg?t=1556305313', 'Supraland', 'intro');
INSERT INTO `image` VALUES ('44', 'https://media.st.dl.bscstorage.net/steam/apps/271590/capsule_616x353.jpg?t=1544815097', 'Grand Theft Auto V', 'post');
INSERT INTO `image` VALUES ('45', 'https://media.st.dl.bscstorage.net/steam/apps/271590/ss_62a2f95c1784f138c3abbecf376c5cc99cc94a57.600x338.jpg', 'Grand Theft Auto V', 'intro');
INSERT INTO `image` VALUES ('46', 'https://media.st.dl.bscstorage.net/steam/apps/271590/ss_a5ca40b51e95d5c592e5eb77b3d78d5149ea5fd6.600x338.jpg', 'Grand Theft Auto V', 'intro');
INSERT INTO `image` VALUES ('47', 'https://media.st.dl.bscstorage.net/steam/apps/271590/ss_e929649b2b98ad76795d92d8489470bc5dbffddb.600x338.jpg', 'Grand Theft Auto V', 'intro');
INSERT INTO `image` VALUES ('48', 'https://media.st.dl.bscstorage.net/steam/apps/271590/ss_1487c2c7ddd9a1ae6b55f7e50d5d63ada6915921.600x338.jpg', 'Grand Theft Auto V', 'intro');
INSERT INTO `image` VALUES ('49', 'https://media.st.dl.bscstorage.net/steam/apps/271590/ss_66daaa8e0416b805ffb9a853235e21468d6b85bc.600x338.jpg?t=1544815097', 'Grand Theft Auto V', 'intro');
INSERT INTO `image` VALUES ('50', 'https://media.st.dl.bscstorage.net/steam/apps/477160/capsule_616x353_schinese.jpg?t=1554729390', 'Human Fall Flat', 'post');
INSERT INTO `image` VALUES ('51', 'https://media.st.dl.bscstorage.net/steam/apps/477160/ss_50d34bf8e2fbc4aafca961f2468bdc95e616b04a.600x338.jpg', 'Human Fall Flat', 'intro');
INSERT INTO `image` VALUES ('52', 'https://media.st.dl.bscstorage.net/steam/apps/477160/ss_fd273bcfc252363b0d1af710c9a273df228c900a.600x338.jpg', 'Human Fall Flat', 'intro');
INSERT INTO `image` VALUES ('53', 'https://media.st.dl.bscstorage.net/steam/apps/477160/ss_bc87677b5a5d9f9041feb22e9a0a2e98051c4b6b.600x338.jpg', 'Human Fall Flat', 'intro');
INSERT INTO `image` VALUES ('54', 'https://media.st.dl.bscstorage.net/steam/apps/477160/ss_54d51aeba62973c72d77bf2fa48e26971db80344.600x338.jpg', 'Human Fall Flat', 'intro');
INSERT INTO `image` VALUES ('55', 'https://media.st.dl.bscstorage.net/steam/apps/477160/ss_4cb369df645490d6597f7d3acf9133bb477cae1f.600x338.jpg?t=1554729390', 'Human Fall Flat', 'intro');
INSERT INTO `image` VALUES ('56', 'https://media.st.dl.bscstorage.net/steam/apps/814380/capsule_616x353_alt_assets_0.jpg?t=1554918884', 'Sekiro™: Shadows Die Twice', 'post');
INSERT INTO `image` VALUES ('57', 'https://media.st.dl.bscstorage.net/steam/apps/814380/ss_552bf9e99b3682d75c01ca4a55ba426e85f3b621.600x338.jpg', 'Sekiro™: Shadows Die Twice', 'intro');
INSERT INTO `image` VALUES ('58', 'https://media.st.dl.bscstorage.net/steam/apps/814380/ss_2036ad636be8fa2c4bf926004f369bb97490350a.600x338.jpg', 'Sekiro™: Shadows Die Twice', 'intro');
INSERT INTO `image` VALUES ('59', 'https://media.st.dl.bscstorage.net/steam/apps/814380/ss_53d73aff4d439cbd9b9b69430f9b3b6db8d0ecd7.600x338.jpg', 'Sekiro™: Shadows Die Twice', 'intro');
INSERT INTO `image` VALUES ('60', 'https://media.st.dl.bscstorage.net/steam/apps/814380/ss_285c1a69bda8182e5c52598d59259f1681b42e5c.600x338.jpg', 'Sekiro™: Shadows Die Twice', 'intro');
INSERT INTO `image` VALUES ('61', 'https://media.st.dl.bscstorage.net/steam/apps/814380/ss_4f21e0652a1a89f0c4fabbd3eae91a1defd23b71.600x338.jpg?t=1554918884', 'Sekiro™: Shadows Die Twice', 'intro');
INSERT INTO `image` VALUES ('62', 'https://media.st.dl.bscstorage.net/steam/apps/602320/capsule_616x353.jpg?t=1555407668', 'Train Valley 2', 'post');
INSERT INTO `image` VALUES ('63', 'https://media.st.dl.bscstorage.net/steam/apps/602320/ss_fd39c649877726e49a5dcfb950af5b0882024be7.600x338.jpg', 'Train Valley 2', 'intro');
INSERT INTO `image` VALUES ('64', 'https://media.st.dl.bscstorage.net/steam/apps/602320/ss_89d7e30e801ce6a6d65f4cbc6a757250060a1f5d.600x338.jpg', 'Train Valley 2', 'intro');
INSERT INTO `image` VALUES ('65', 'https://media.st.dl.bscstorage.net/steam/apps/602320/ss_2d63bc214ae1cbbb5562b343bdd8e107ca15ab84.600x338.jpg', 'Train Valley 2', 'intro');
INSERT INTO `image` VALUES ('66', 'https://media.st.dl.bscstorage.net/steam/apps/602320/ss_20b720981c3c9abd7e4c6b1968baba9ebef76a2d.600x338.jpg', 'Train Valley 2', 'intro');
INSERT INTO `image` VALUES ('67', 'https://media.st.dl.bscstorage.net/steam/apps/602320/ss_8a3d30756d9ce51b09bc7c0ae63dc288ffdd83ea.600x338.jpg?t=1555449280', 'Train Valley 2', 'intro');
INSERT INTO `image` VALUES ('68', 'https://media.st.dl.bscstorage.net/steam/apps/750920/capsule_616x353.jpg?t=1556037195', 'Shadow of the Tomb Raider', 'post');
INSERT INTO `image` VALUES ('69', 'https://media.st.dl.bscstorage.net/steam/apps/750920/ss_8907e0a624a1113be01fa1b426d0e3ab0971e7d2.600x338.jpg', 'Shadow of the Tomb Raider', 'intro');
INSERT INTO `image` VALUES ('70', 'https://media.st.dl.bscstorage.net/steam/apps/750920/ss_191adc1f11bf9d13498cb411ac71f29221732e86.600x338.jpg', 'Shadow of the Tomb Raider', 'intro');
INSERT INTO `image` VALUES ('71', 'https://media.st.dl.bscstorage.net/steam/apps/750920/ss_794a3ecd4ae51313f8cfffbc6b3d8b91c665b12b.600x338.jpg', 'Shadow of the Tomb Raider', 'intro');
INSERT INTO `image` VALUES ('72', 'https://media.st.dl.bscstorage.net/steam/apps/750920/ss_04b30aaa0ce083b1bcff63d06432707ab9c35c74.600x338.jpg?t=1556037195', 'Shadow of the Tomb Raider', 'intro');
INSERT INTO `image` VALUES ('73', 'https://media.st.dl.bscstorage.net/steam/apps/750920/ss_7496de2518ddb1b58db0004d1386b4e48c442367.600x338.jpg?t=1556037195', 'Shadow of the Tomb Raider', 'intro');
INSERT INTO `image` VALUES ('74', 'https://media.st.dl.bscstorage.net/steam/apps/632360/header.jpg?t=1556283641', 'Risk of Rain 2', 'post');
INSERT INTO `image` VALUES ('75', 'https://media.st.dl.bscstorage.net/steam/apps/632360/ss_85548e86c50ff654c6a49235ea686a956f8ee9ec.600x338.jpg?t=1556283641', 'Risk of Rain 2', 'intro');
INSERT INTO `image` VALUES ('76', 'https://media.st.dl.bscstorage.net/steam/apps/632360/ss_aece490dab1384513c7250df3043973ff396ff29.600x338.jpg?t=1556283641', 'Risk of Rain 2', 'intro');
INSERT INTO `image` VALUES ('77', 'https://media.st.dl.bscstorage.net/steam/apps/632360/ss_c9cfce27cb143a075fcd468405c3b27855fc6427.600x338.jpg?t=1556283641', 'Risk of Rain 2', 'intro');
INSERT INTO `image` VALUES ('78', 'https://media.st.dl.bscstorage.net/steam/apps/632360/ss_121003c290030ab310e1651a442609939141911e.600x338.jpg?t=1556283641', 'Risk of Rain 2', 'intro');
INSERT INTO `image` VALUES ('79', 'https://media.st.dl.bscstorage.net/steam/apps/632360/ss_f25a60838e021cb214295f773fe7caca575792ea.600x338.jpg?t=1556283641', 'Risk of Rain 2', 'intro');
INSERT INTO `image` VALUES ('80', 'https://media.st.dl.bscstorage.net/steam/apps/582010/header.jpg?t=1554771889', 'MONSTER HUNTER: WORLD', 'post');
INSERT INTO `image` VALUES ('81', 'https://media.st.dl.bscstorage.net/steam/apps/582010/ss_6b4986a37c7b5c185a796085c002febcdd5357b5.600x338.jpg?t=1554771889', 'MONSTER HUNTER: WORLD', 'intro');
INSERT INTO `image` VALUES ('82', 'https://media.st.dl.bscstorage.net/steam/apps/582010/ss_0dfb20f6f09c196bfc317bd517dc430ed6e6a2a4.600x338.jpg?t=1554771889', 'MONSTER HUNTER: WORLD', 'intro');
INSERT INTO `image` VALUES ('83', 'https://media.st.dl.bscstorage.net/steam/apps/582010/ss_25902a9ae6977d6d10ebff20b87e8739e51c5b8b.600x338.jpg?t=1554771889', 'MONSTER HUNTER: WORLD', 'intro');
INSERT INTO `image` VALUES ('84', 'https://media.st.dl.bscstorage.net/steam/apps/582010/ss_ce69dc57e6e442c73d874f1b701f2e4af405fb19.600x338.jpg?t=1554771889', 'MONSTER HUNTER: WORLD', 'intro');
INSERT INTO `image` VALUES ('85', 'https://media.st.dl.bscstorage.net/steam/apps/582010/ss_6d26868b45c20bf4dd5f75f31264aca08ce17217.600x338.jpg?t=1554771889', 'MONSTER HUNTER: WORLD', 'intro');
INSERT INTO `image` VALUES ('86', 'https://media.st.dl.bscstorage.net/steam/apps/883710/header_schinese.jpg?t=1556224097', 'RESIDENT EVIL 2 / BIOHAZARD RE:2', 'post');
INSERT INTO `image` VALUES ('87', 'https://media.st.dl.bscstorage.net/steam/apps/883710/ss_1392581cd29817e44099cf05416b70ffb159c58b.600x338.jpg?t=1556224097', 'RESIDENT EVIL 2 / BIOHAZARD RE:2', 'intro');
INSERT INTO `image` VALUES ('88', 'https://media.st.dl.bscstorage.net/steam/apps/883710/ss_153bd5afa0b45e74b0242a3805dc29aab9f1685e.600x338.jpg?t=1556224097', 'RESIDENT EVIL 2 / BIOHAZARD RE:2', 'intro');
INSERT INTO `image` VALUES ('89', 'https://media.st.dl.bscstorage.net/steam/apps/883710/ss_cd289d2e809b3fcb236a4f193a43a16787c6bc87.600x338.jpg?t=1556224097', 'RESIDENT EVIL 2 / BIOHAZARD RE:2', 'intro');
INSERT INTO `image` VALUES ('90', 'https://media.st.dl.bscstorage.net/steam/apps/883710/ss_12fb940f490e3ec565fd41417156969632260d5a.600x338.jpg?t=1556224097', 'RESIDENT EVIL 2 / BIOHAZARD RE:2', 'intro');
INSERT INTO `image` VALUES ('91', 'https://media.st.dl.bscstorage.net/steam/apps/883710/ss_90cc41d4c52d56f5ab26ad64223922a93b60eba6.600x338.jpg?t=1556224097', 'RESIDENT EVIL 2 / BIOHAZARD RE:2', 'intro');
INSERT INTO `image` VALUES ('92', 'https://media.st.dl.bscstorage.net/steam/apps/622220/capsule_616x353.jpg?t=1553623017', 'Fate/EXTELLA LINK', 'post');
INSERT INTO `image` VALUES ('93', 'https://media.st.dl.bscstorage.net/steam/apps/622220/ss_d452a9a37defc5b31784a29cbd97c507abaa3a7e.600x338.jpg', 'Fate/EXTELLA LINK', 'intro');
INSERT INTO `image` VALUES ('94', 'https://media.st.dl.bscstorage.net/steam/apps/622220/ss_3a841beaa437e811017d969a18652a6ec053e460.600x338.jpg', 'Fate/EXTELLA LINK', 'intro');
INSERT INTO `image` VALUES ('95', 'https://media.st.dl.bscstorage.net/steam/apps/622220/ss_0fb3dc7bc9fe070c31b25e4348f240ac0f1b923d.600x338.jpg', 'Fate/EXTELLA LINK', 'intro');
INSERT INTO `image` VALUES ('96', 'https://media.st.dl.bscstorage.net/steam/apps/622220/ss_838b11ae934b1701dcf5e1989161d7540424fcec.600x338.jpg', 'Fate/EXTELLA LINK', 'intro');
INSERT INTO `image` VALUES ('97', 'https://media.st.dl.bscstorage.net/steam/apps/622220/ss_aaea772b5c96e9b9fd12052540aff67bbfcce979.600x338.jpg?t=1553623017', 'Fate/EXTELLA LINK', 'intro');
INSERT INTO `image` VALUES ('98', 'https://media.st.dl.bscstorage.net/steam/apps/374320/capsule_616x353.jpg?t=1553251330', 'DARK SOULS™ III', 'post');
INSERT INTO `image` VALUES ('99', 'https://media.st.dl.bscstorage.net/steam/apps/374320/ss_1318a04ef11d87f38aebe6d47a96124f8f888ca8.600x338.jpg', 'DARK SOULS™ III', 'intro');
INSERT INTO `image` VALUES ('100', 'https://media.st.dl.bscstorage.net/steam/apps/374320/ss_da36c88ae35d4f20c9d221a79592b31c080521d2.600x338.jpg', 'DARK SOULS™ III', 'intro');
INSERT INTO `image` VALUES ('101', 'https://media.st.dl.bscstorage.net/steam/apps/374320/ss_27397db724cfd5648655c1056ff5d184147a4c50.600x338.jpg', 'DARK SOULS™ III', 'intro');
INSERT INTO `image` VALUES ('102', 'https://media.st.dl.bscstorage.net/steam/apps/374320/ss_12c4d9a3c04d6d340ffea9335441eb2ad84e0028.600x338.jpg', 'DARK SOULS™ III', 'intro');
INSERT INTO `image` VALUES ('103', 'https://media.st.dl.bscstorage.net/steam/apps/374320/ss_975ca4966b9b627f8d9bb0d2c9b6743dfceac6da.600x338.jpg?t=1553251330', 'DARK SOULS™ III', 'intro');
INSERT INTO `image` VALUES ('104', 'https://media.st.dl.bscstorage.net/steam/apps/637650/capsule_616x353.jpg?t=1553563213', 'FINAL FANTASY XV WINDOWS EDITION', 'post');
INSERT INTO `image` VALUES ('105', 'https://media.st.dl.bscstorage.net/steam/apps/637650/ss_999e73c2cb361d41451d1a84d85f3ff59aa30110.600x338.jpg', 'FINAL FANTASY XV WINDOWS EDITION', 'intro');
INSERT INTO `image` VALUES ('106', 'https://media.st.dl.bscstorage.net/steam/apps/637650/ss_031800eb49e2c1da5d8a31f8fe0bbb64544c2d0a.600x338.jpg', 'FINAL FANTASY XV WINDOWS EDITION', 'intro');
INSERT INTO `image` VALUES ('107', 'https://media.st.dl.bscstorage.net/steam/apps/637650/ss_da4aaf619760c0f075e735bcc4f2f8b850c0c581.600x338.jpg', 'FINAL FANTASY XV WINDOWS EDITION', 'intro');
INSERT INTO `image` VALUES ('108', 'https://media.st.dl.bscstorage.net/steam/apps/637650/ss_1b02d71822001f88219574bd02d65004d26a4299.600x338.jpg', 'FINAL FANTASY XV WINDOWS EDITION', 'intro');
INSERT INTO `image` VALUES ('109', 'https://media.st.dl.bscstorage.net/steam/apps/637650/ss_246a43b1fdf8c140842a38c2f96e788ea77cfd12.600x338.jpg?t=1553563213', 'FINAL FANTASY XV WINDOWS EDITION', 'intro');
INSERT INTO `image` VALUES ('110', 'https://media.st.dl.bscstorage.net/steam/apps/626690/capsule_616x353.jpg?t=1547787378', 'Sword Art Online: Fatal Bullet', 'post');
INSERT INTO `image` VALUES ('111', 'https://media.st.dl.bscstorage.net/steam/apps/626690/ss_cb59554eb2a29d497de4ae71621c89fd8be136f9.600x338.jpg', 'Sword Art Online: Fatal Bullet', 'intro');
INSERT INTO `image` VALUES ('112', 'https://media.st.dl.bscstorage.net/steam/apps/626690/ss_679d9268da71672f27907058fcbca3e6906edd06.600x338.jpg', 'Sword Art Online: Fatal Bullet', 'intro');
INSERT INTO `image` VALUES ('113', 'https://media.st.dl.bscstorage.net/steam/apps/626690/ss_e0286510ed74eff1d44f772185e594dde2fe3339.600x338.jpg', 'Sword Art Online: Fatal Bullet', 'intro');
INSERT INTO `image` VALUES ('114', 'https://media.st.dl.bscstorage.net/steam/apps/626690/ss_fc347e6cec048bcd362b5378ffa9c04c6d81952b.600x338.jpg', 'Sword Art Online: Fatal Bullet', 'intro');
INSERT INTO `image` VALUES ('115', 'https://media.st.dl.bscstorage.net/steam/apps/626690/ss_abd01760b17f8571026916c65df86b83594462af.600x338.jpg?t=1547787378', 'Sword Art Online: Fatal Bullet', 'intro');
INSERT INTO `image` VALUES ('116', 'https://media.st.dl.bscstorage.net/steam/apps/346110/capsule_616x353.jpg?t=1556645672', 'ARK: Survival Evolved', 'post');
INSERT INTO `image` VALUES ('117', 'https://media.st.dl.bscstorage.net/steam/apps/346110/ss_2fd997a2f7151cb2187043a1f41589cc6a9ebf3a.600x338.jpg', 'ARK: Survival Evolved', 'intro');
INSERT INTO `image` VALUES ('118', 'https://media.st.dl.bscstorage.net/steam/apps/346110/ss_46778c08a1a5ac5bdbaf8a5bf844fa666f66a14b.600x338.jpg', 'ARK: Survival Evolved', 'intro');
INSERT INTO `image` VALUES ('119', 'https://media.st.dl.bscstorage.net/steam/apps/346110/ss_01cbef83fe28d64ee5a3d39a86043fb1e49abd31.600x338.jpg', 'ARK: Survival Evolved', 'intro');
INSERT INTO `image` VALUES ('120', 'https://media.st.dl.bscstorage.net/steam/apps/346110/ss_164a92a53f9bcbb728b391fc0719f9769c2e1249.600x338.jpg', 'ARK: Survival Evolved', 'intro');
INSERT INTO `image` VALUES ('121', 'https://media.st.dl.bscstorage.net/steam/apps/346110/ss_7f9c3429b86d65cd63beed4597a23148d7cadf08.600x338.jpg?t=1556645672', 'ARK: Survival Evolved', 'intro');
INSERT INTO `image` VALUES ('122', '127.0.0.1:8888/290580.jpg', 'avatar', 'avatar');
INSERT INTO `image` VALUES ('123', '127.0.0.1:8888/img/679020.png', 'avatar', 'avatar');
INSERT INTO `image` VALUES ('124', '127.0.0.1:8888/img/602575.png', 'avatar', 'avatar');
INSERT INTO `image` VALUES ('125', '127.0.0.1:8888/img/679020(1).png', 'avatar', 'avatar');
INSERT INTO `image` VALUES ('126', 'http://127.0.0.1:8888/img/679020(2).png', 'avatar', 'avatar');
INSERT INTO `image` VALUES ('127', 'http://127.0.0.1:8888/img/697788.png', 'avatar', 'avatar');
INSERT INTO `image` VALUES ('128', 'http://127.0.0.1:8888/img/679020(3).png', 'avatar', 'avatar');

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `hotnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES ('1', '视觉小说', '30');
INSERT INTO `label` VALUES ('2', '冒险', '30');
INSERT INTO `label` VALUES ('3', '动漫', '31');
INSERT INTO `label` VALUES ('4', '恋爱模拟', '32');
INSERT INTO `label` VALUES ('5', '生存', '43');
INSERT INTO `label` VALUES ('6', '开放世界', '32');
INSERT INTO `label` VALUES ('7', '恐怖', '23');
INSERT INTO `label` VALUES ('8', '制作', '32');
INSERT INTO `label` VALUES ('9', '建造', '32');
INSERT INTO `label` VALUES ('10', '格斗', '2');
INSERT INTO `label` VALUES ('11', '2D', '2');
INSERT INTO `label` VALUES ('12', '角色扮演', '2');
INSERT INTO `label` VALUES ('13', '剧情丰富', '2');
INSERT INTO `label` VALUES ('14', '射击', '2');
INSERT INTO `label` VALUES ('15', '多人', '1');
INSERT INTO `label` VALUES ('16', '策略', '1');
INSERT INTO `label` VALUES ('17', '探索', '1');
INSERT INTO `label` VALUES ('18', '经济', '1');
INSERT INTO `label` VALUES ('19', '解谜', '1');
INSERT INTO `label` VALUES ('20', '第一人称', '1');
INSERT INTO `label` VALUES ('21', '犯罪', '1');
INSERT INTO `label` VALUES ('22', '欢乐', '1');
INSERT INTO `label` VALUES ('23', '合作', '1');
INSERT INTO `label` VALUES ('24', '物理', '1');
INSERT INTO `label` VALUES ('25', '单人', '1');
INSERT INTO `label` VALUES ('26', '忍者', '1');
INSERT INTO `label` VALUES ('27', '潜行', '1');
INSERT INTO `label` VALUES ('28', '休闲', '1');
INSERT INTO `label` VALUES ('29', '火车', '1');
INSERT INTO `label` VALUES ('30', '僵尸', '1');
INSERT INTO `label` VALUES ('32', '魂系列', '0');
INSERT INTO `label` VALUES ('33', '猎杀', '0');
INSERT INTO `label` VALUES ('34', '砍杀', '0');
INSERT INTO `label` VALUES ('35', '二战', '0');
INSERT INTO `label` VALUES ('36', '甜蜜', '0');

-- ----------------------------
-- Table structure for language
-- ----------------------------
DROP TABLE IF EXISTS `language`;
CREATE TABLE `language` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of language
-- ----------------------------

-- ----------------------------
-- Table structure for platform
-- ----------------------------
DROP TABLE IF EXISTS `platform`;
CREATE TABLE `platform` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform
-- ----------------------------

-- ----------------------------
-- Table structure for publisher
-- ----------------------------
DROP TABLE IF EXISTS `publisher`;
CREATE TABLE `publisher` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of publisher
-- ----------------------------
INSERT INTO `publisher` VALUES ('1', 'StudioKanata');
INSERT INTO `publisher` VALUES ('2', '\r\nEndnight Games Ltd');

-- ----------------------------
-- Table structure for recentgame
-- ----------------------------
DROP TABLE IF EXISTS `recentgame`;
CREATE TABLE `recentgame` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `gameid` bigint(40) DEFAULT NULL,
  `lastplay` date DEFAULT NULL,
  `playtime` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recentgame
-- ----------------------------
INSERT INTO `recentgame` VALUES ('1', '473721601@qq.com', '10', '2019-06-18', '30');
INSERT INTO `recentgame` VALUES ('2', '473721601@qq.com', '16', '2019-06-07', '4');
INSERT INTO `recentgame` VALUES ('3', '473721601@qq.com', '20', '2019-06-20', '78');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `userid` bigint(40) DEFAULT NULL,
  `posterimage` bigint(40) DEFAULT NULL,
  `gameprice` int(11) DEFAULT NULL,
  `gamename` varchar(255) DEFAULT NULL,
  `gameid` bigint(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES ('26', '6', '2', '123', '樱花片落恋模样 This is a very sweet love story.', '1');
INSERT INTO `shoppingcart` VALUES ('27', '6', '44', '290', 'Grand Theft Auto V', '8');

-- ----------------------------
-- Table structure for spikegame
-- ----------------------------
DROP TABLE IF EXISTS `spikegame`;
CREATE TABLE `spikegame` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `gameid` bigint(40) DEFAULT NULL,
  `postergame` bigint(40) DEFAULT NULL,
  `spikeprice` int(11) DEFAULT NULL,
  `stockcount` int(11) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `gameprice` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spikegame
-- ----------------------------
INSERT INTO `spikegame` VALUES ('1', '1', '2', '1', '8', '2019-06-19 11:00:00', '2019-06-19 11:01:00', '136');

-- ----------------------------
-- Table structure for spikeshopcart
-- ----------------------------
DROP TABLE IF EXISTS `spikeshopcart`;
CREATE TABLE `spikeshopcart` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `userid` bigint(40) DEFAULT NULL,
  `spikegameid` bigint(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spikeshopcart
-- ----------------------------

-- ----------------------------
-- Table structure for systemneed
-- ----------------------------
DROP TABLE IF EXISTS `systemneed`;
CREATE TABLE `systemneed` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operatingsystem` varchar(255) DEFAULT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `graphicscard` varchar(255) DEFAULT NULL,
  `directx` varchar(255) DEFAULT NULL,
  `network` varchar(255) DEFAULT NULL,
  `rom` varchar(255) DEFAULT NULL,
  `soundcard` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemneed
-- ----------------------------
INSERT INTO `systemneed` VALUES ('1', 'Windows VISTA/7/8/10', 'Pentium', '1 GB RAM', 'NVIDIA GeForce GTX 760', '9.0c', '无', '需要 4 GB 可用空间', 'DirectX 11 Compatible');
INSERT INTO `systemneed` VALUES ('2', 'Windows VISTA/7/8/10', 'Intel I5及以上', '2GB RAM', 'NVIDIA GeForce GTX 760', '11.0c', '无', '需要 4 GB 可用空间', 'DirectX 11 Compatible');
INSERT INTO `systemneed` VALUES ('3', ' Windows 7', 'Intel Dual-Core 2.4 GHz', '4 GB RAM', 'NVIDIA GeForce 8800GT', '9.0', '无', ' 需要 5 GB 可用空间', ' DirectX®-compatible');
INSERT INTO `systemneed` VALUES ('4', 'Windows 7', 'Quad Core Processor', '4 GB RAM', ' NVIDIA GeForce GTX 560', ' 9.0', '无', '需要 5 GB 可用空间', 'DirectX®-compatible');
INSERT INTO `systemneed` VALUES ('5', 'Windows 7 / 8 / 8.1', 'Intel Core2 Duo', ' 2 GB RAM', 'nVidia GeForce 7900 GT or better / AMD Radeon X1900 / nVidia GeForce GT 620 (Windows 8.1)', '9.0', '无', '需要 10 GB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('6', 'Windows 7 / 8 / 8.1', ' Intel Core i5 / i7', ' 4 GB RAM', 'nVidia GeForce 8800 GT or better / AMD Radeon HD3700 / nVidia GeForce GT 650 (Windows 8.1)', '9.0', '无', '需要 10 GB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('7', 'Windows 7', 'Core i3 / AMD A6 2.4Ghz', '2 GB RAM', 'nVidia GeForce 8800 GT or better / AMD Radeon HD3700 / nVidia GeForce GT 650 (Windows 8.1)', '11', '需要', ' 需要 500 MB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('8', 'Windows 7', 'Core i3 / AMD A6 2.4Ghz', '2 GB RAM', 'nVidia GeForce 8800 GT or better / AMD Radeon HD3700 / nVidia GeForce GT 650 (Windows 8.1)', '11', '需要', ' 需要 500 MB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('9', '64-bit Windows 7, Windows 8.1, Windows 10', 'Intel Core i5-4430 / AMD FX-6300', '8 GB RAM', 'NVIDIA GeForce GTX 960 2GB / AMD Radeon R7 370 2GB', '11', '需要', ' 需要 30 GB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('10', ' 64-bit Windows 7, Windows 8.1, Windows 10', 'Intel Core i5-6600K / AMD Ryzen 5 1600', '16 GB RAM', ' NVIDIA GeForce GTX 1060 3GB / AMD Radeon RX 580 4GB', '11', '需要', '需要 30 GB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('11', 'Windows 7 SP1, Windows 8.1 or Windows 10Microsoft Windows 7, Windows 8.1 or Windows 10(64-bit versions only)', ' Intel i5 3470, AMD FX 6350', '8 GB RAM', 'NVIDIA GeForce 670 GTX or AMD Radeon R9 285 (2 GB of VRAM, Shader Model 5.0)', '11', '无', '需要 60 GB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('12', 'Microsoft Windows 7, Windows 8.1 or Windows 10(64-bit versions only)', 'Intel i5 4690k, AMD Ryzen 5 1400', '8 GB RAM', 'NVIDIA GeForce 970 GTX or AMD Radeon RX 480 (4 GB of VRAM, Shader Model 5.1)', '12', '无', ' 需要 60 GB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('13', 'Windows 7', 'Intel Core2Duo 2.66GHz', ' 4 GB RAM', 'GTX 780', '11', '无', '需要 3 GB 可用空间', 'DirectX®-compatible');
INSERT INTO `systemneed` VALUES ('14', ' Windows 10', 'Intel Core2Duo 2.66GHz', '4 GB RAM', 'GTX 1050ti', '11', '无', '需要 3 GB 可用空间', 'DirectX®-compatible');
INSERT INTO `systemneed` VALUES ('15', 'Windows 10 64 Bit, Windows 8.1 64 Bit, Windows 8 64 Bit, Windows 7 64 Bit Service Pack 1', 'Intel Core 2 Quad CPU Q6600 @ 2.40GHz (4 CPUs) / AMD Phenom 9850 Quad-Core Processor (4 CPUs) @ 2.5GHz', '4 GB RAM', 'NVIDIA 9800 GT 1GB / AMD HD 4870 1GB (DX 10, 10.1, 11)', '11', '无', '需要 72 GB 可用空间', ' 100% DirectX 10 compatible');
INSERT INTO `systemneed` VALUES ('16', 'Windows 10 64 Bit, Windows 8.1 64 Bit, Windows 8 64 Bit, Windows 7 64 Bit Service Pack 1', 'Intel Core i5 3470 @ 3.2GHz (4 CPUs) / AMD X8 FX-8350 @ 4GHz (8 CPUs)', '8 GB RAM', 'NVIDIA GTX 660 2GB / AMD HD 7870 2GB', '12', '无', '需要 72 GB 可用空间', '100% DirectX 10 compatible');
INSERT INTO `systemneed` VALUES ('17', 'Windows XP/Vista/7/8/8.1/10 x86 and x64', 'Intel Core2 Duo E6750 (2 * 2660)', '1024 MB RAM', ' GeForce GT 740 (2048 MB) or equivalent | Radeon HD 5770 (1024 MB)', '11', '无', '需要 500 MB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('18', 'Windows XP/Vista/7/8/8.1/10 x86 and x64', 'Intel Core2 Quad Q9300 (4 * 2500) ', '2048 MB RAM', ' GeForce GTX 460 (1024 MB) or equivalent | Radeon HD 7770 (1024 MB)', '12', '无', '需要 500 MB 可用空间', '100% DirectX 10 compatible');
INSERT INTO `systemneed` VALUES ('19', 'Windows 7 64-bit | Windows 8 64-bit | Windows 10 64-bit', 'Intel Core i3-2100 | AMD FX-6300', '4 GB RAM', 'NVIDIA GeForce GTX 760 | AMD Radeon HD 7950', '11', '需要', '需要 25 GB 可用空间', 'DirectX 11 Compatible');
INSERT INTO `systemneed` VALUES ('20', ' Windows 7 64-bit | Windows 8 64-bit | Windows 10 64-bit', 'Intel Core i5-2500K | AMD Ryzen 5 1400', '8 GB RAM', 'NVIDIA GeForce GTX 970 | AMD Radeon RX 570', '12', '需要', ' 需要 25 GB 可用空间', 'DirectX 11 Compatible');
INSERT INTO `systemneed` VALUES ('21', 'Windows Vista SP1+', 'Intel Core 2 Duo E4500 @ 2.2GHz or AMD Athlon 64 X2 5600+ @ 2.8 GHz', ' 2 GB RAM', 'ATi Radeon HD 2400 or NVIDIA GeForce 7600', '9', '无', '需要 1 GB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('22', 'Windows Vista SP1+', 'Intel Core 2 Duo E4500 @ 2.2GHz or AMD Athlon 64 X2 5600+ @ 2.8 GHz', ' 2 GB RAM', 'ATi Radeon HD 2400 or NVIDIA GeForce 7600', '9', '无', '需要 1 GB 可用空间', 'Direct Sound');
INSERT INTO `systemneed` VALUES ('23', 'Windows 7 64-bit | Windows 8 64-bit | Windows 10 64-bit', 'Intel Core i3-2100 | AMD FX-6300', '4 GB RAM', 'NVIDIA GeForce GTX 760 | AMD Radeon HD 7950', '11', '无', '需要 25 GB 可用空间', 'DirectX 11 Compatible');
INSERT INTO `systemneed` VALUES ('24', ' Windows 7 64-bit | Windows 8 64-bit | Windows 10 64-bit', 'Intel Core i5-2500K | AMD Ryzen 5 1400', '8 GB RAM', 'NVIDIA GeForce GTX 970 | AMD Radeon RX 570', '12', '无', ' 需要 25 GB 可用空间', 'DirectX 11 Compatible');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `typename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '动作');
INSERT INTO `type` VALUES ('2', '冒险');
INSERT INTO `type` VALUES ('3', '独立');
INSERT INTO `type` VALUES ('4', '模拟');
INSERT INTO `type` VALUES ('5', '格斗');
INSERT INTO `type` VALUES ('6', '角色扮演');
INSERT INTO `type` VALUES ('7', '多人');
INSERT INTO `type` VALUES ('8', '策略');
INSERT INTO `type` VALUES ('9', '休息');
INSERT INTO `type` VALUES ('10', '免费');
INSERT INTO `type` VALUES ('11', '射击');
INSERT INTO `type` VALUES ('12', '建造');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `avatar` bigint(40) DEFAULT NULL,
  `playtime` double DEFAULT NULL,
  `commentnum` int(11) DEFAULT NULL,
  `buygames` int(11) DEFAULT NULL,
  `isadmin` int(11) DEFAULT NULL,
  `lv` int(11) DEFAULT '0',
  `country` varchar(255) DEFAULT 'china',
  `province` varchar(255) DEFAULT 'hunan',
  `introduction` varchar(255) DEFAULT '这个人很懒，没有什么~~',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Suyeq', '1q2w3e', '473721601@qq.com', '96bd8eabbac36ead0485d163a1cff489', '126', '109.5', '0', '30', '1', '9', 'China', 'Hunan', '一两清风，半盏明月');
INSERT INTO `user` VALUES ('2', 'Suye', '1q2w3e', '12345678@qq.com', '96bd8eabbac36ead0485d163a1cff489', '1', '456.9', '20', '40', '0', '0', 'China', 'Hunan', '这个人很懒，没有什么~~');
INSERT INTO `user` VALUES ('6', '李檬', 'c075b5', '3162618364@qq.com', '242ebf1e943eb157cdf93b81e2e7f9ed', '1', '0', '1', '2', '0', '0', 'china', 'Hunan', '一两清风，半盏明月');

-- ----------------------------
-- Table structure for user_game
-- ----------------------------
DROP TABLE IF EXISTS `user_game`;
CREATE TABLE `user_game` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) DEFAULT NULL,
  `gameid` bigint(40) DEFAULT NULL,
  `playtime` int(11) DEFAULT '0',
  `lastplay` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_game
-- ----------------------------
INSERT INTO `user_game` VALUES ('1', '473721601@qq.com', '10', '10', '2019-06-11');
INSERT INTO `user_game` VALUES ('2', '473721601@qq.com', '16', '70', '2019-06-08');
INSERT INTO `user_game` VALUES ('3', '473721601@qq.com', '1', '20', '2019-06-05');
INSERT INTO `user_game` VALUES ('4', '473721601@qq.com', '20', '60', '2019-06-02');
INSERT INTO `user_game` VALUES ('5', '3162618364@qq.com', '20', '10', '2019-06-17');
INSERT INTO `user_game` VALUES ('6', '3162618364@qq.com', '10', '50', '2019-06-27');
INSERT INTO `user_game` VALUES ('7', '3162618364@qq.com', '5', '10', '2019-06-18');
INSERT INTO `user_game` VALUES ('8', '473721601@qq.com', '18', '0', null);
