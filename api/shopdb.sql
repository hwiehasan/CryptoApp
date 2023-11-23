-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 12, 2023 at 08:22 AM
-- Server version: 5.7.17
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shopdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `catID` int(3) UNSIGNED NOT NULL,
  `catName` varchar(100) COLLATE utf8_persian_ci NOT NULL,
  `catParent` int(3) UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`catID`, `catName`, `catParent`) VALUES
(1, 'Web3', 1),
(2, 'DeFi', 1),
(3, 'IoT', 1),
(4, 'Stablecoin', 1);

-- --------------------------------------------------------

--
-- Table structure for table `kala`
--

CREATE TABLE `kala` (
  `kID` int(10) UNSIGNED NOT NULL,
  `kName` varchar(100) COLLATE utf8_persian_ci NOT NULL,
  `price` int(10) UNSIGNED DEFAULT NULL,
  `weight` varchar(24) COLLATE utf8_persian_ci DEFAULT NULL,
  `num` varchar(10) COLLATE utf8_persian_ci DEFAULT NULL,
  `color` varchar(24) COLLATE utf8_persian_ci DEFAULT NULL,
  `catID` int(3) UNSIGNED NOT NULL,
  `image` varchar(200) COLLATE utf8_persian_ci NOT NULL DEFAULT 'default-image.png',
  `description` varchar(1000) COLLATE utf8_persian_ci DEFAULT NULL,
  `userID` int(8) UNSIGNED NOT NULL,
  `submitDate` datetime DEFAULT '2023-06-09 01:31:00',
  `view` int(10) UNSIGNED DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `kala`
--

INSERT INTO `kala` (`kID`, `kName`, `price`, `weight`, `num`, `color`, `catID`, `image`, `description`, `userID`, `submitDate`, `view`) VALUES
(15, 'Tether USDT', 1, '', '', '', 4, 'usdt.png', 'USDT is a stablecoin (stable-value cryptocurrency) that mirrors the price of the U.S. dollar, issued by a Hong Kong-based company Tether. The token’s peg to the USD is achieved via maintaining a sum of commercial paper, fiduciary deposits, cash, reserve repo notes, and treasury bills in reserves that is equal in USD value to the number of USDT in circulation.', 8, '2022-11-29 09:05:40', 2),
(8, 'USD Coin USDC', 1, '', '', '', 4, 'usdc.png', 'USD Coin (known by its ticker USDC) is a stablecoin that is pegged to the U.S. dollar on a 1:1 basis. Every unit of this cryptocurrency in circulation is backed up by $1 that is held in reserve, in a mix of cash and short-term U.S. Treasury bonds. The Centre consortium, which is behind this asset, says USDC is issued by regulated financial institutions.', 8, '2022-11-07 12:45:21', 10),
(10, 'Binance USD BUSD', 1, '', '', '', 4, 'busd.png', 'Binance USD (BUSD) is a 1:1 USD-backed stable coin issued by Binance (in partnership with Paxos), Approved and regulated by the New York State Department of Financial Services (NYDFS), The BUSD Monthly Audit Report can be viewed from the official website. Launched on 5 Sep 2019, BUSD aims to meld the stability of the dollar with blockchain technology. It is a digital fiat currency, issued as ERC-20 and supports BEP-2', 8, '2022-11-07 01:13:38', 20),
(12, 'DAI', 1, '', '', '', 4, 'dai.png', 'DAI is an Ethereum-based stablecoin (stable-price cryptocurrency) whose issuance and development is managed by the Maker Protocol and the MakerDAO decentralized autonomous organization.', 8, '2022-11-29 09:03:52', 124),
(16, 'VeChain VET', 20, '', '', '', 3, 'vet.png', 'VeChain (VET) is a versatile enterprise-grade L1 smart contract platform.\r\nVeChain began in 2015 as a private consoritium chain, working with a host of enterprises to explore applications of blockchain. VeChain would begin their transition to public blockchain in 2017 with the ERC-20 token VEN, before launching a mainnet of their own in 2018 using the ticker VET.', 8, '2023-06-21 06:25:00', 85),
(17, 'Wrapped Bitcoin WBTC', 25000, '', '', '', 2, 'wbtc.png', 'Wrapped Bitcoin is a tokenized version of Bitcoin (BTC) that runs on the Ethereum (ETH) blockchain.\r\n\r\nWBTC is compliant with ERC-20 — the basic compatibility standard of the Ethereum blockchain — allowing it to be fully integrated into the latter’s ecosystem of decentralized exchanges, crypto lending services, prediction markets and other ERC-20-enabled decentralized finance (DeFi) applications.', 8, '2023-06-07 12:00:00', 46),
(18, 'Avalanche AVAX', 11, '', '', '', 2, 'avax.png', 'Avalanche is a layer one blockchain that functions as a platform for decentralized applications and custom blockchain networks. It is one of Ethereum’s rivals, aiming to unseat Ethereum as the most popular blockchain for smart contracts. It aims to do so by having a higher transaction output of up to 6,500 transactions per second while not compromising scalability.', 8, '2023-06-08 00:41:00', 28),
(19, 'Polkadot DOT', 4, '', '', '', 1, 'dot.png', 'Polkadot is an open-source sharded multichain protocol that connects and secures a network of specialized blockchains, facilitating cross-chain transfer of any data or asset types, not just tokens, thereby allowing blockchains to be interoperable with each other. Polkadot was designed to provide a foundation for a decentralized internet of blockchains, also known as Web3.', 8, '2023-06-01 00:00:00', 200),
(20, 'Internet Computer ICP', 3, '', '', '', 1, 'icp.png', 'The Internet Computer blockchain incorporates a radical rethink of blockchain design, powered by innovations in cryptography. It provides the first “World Computer” blockchain that can be used to build almost any online system or service, including demanding web social media, without need for traditional IT such as cloud computing services. As such it can enable full end-to-end decentralization.', 8, '2023-06-07 00:31:00', 50),
(21, 'Chainlink LINK', 5, '', '', '', 1, 'link.png', 'Founded in 2017, Chainlink is a blockchain abstraction layer that enables universally connected smart contracts. Through a decentralized oracle network, Chainlink allows blockchains to securely interact with external data feeds, events and payment methods, providing the critical off-chain information needed by complex smart contracts to become the dominant form of digital agreement.', 8, '2023-06-21 07:00:00', 560),
(22, 'Filecoin FIL', 3, '', '', '', 1, 'fil.png', 'Filecoin is a decentralized storage system that aims to “store humanity’s most important information.” The project raised $205 million in an initial coin offering (ICO) in 2017, and initially planned a launch date for mid-2019. However, the launch date for the Filecoin mainnet was pushed back until block 148,888, which is expected in mid-October 2020.', 9, '2023-06-09 07:00:00', 22),
(23, 'Aave AAVE', 50, '', '', '', 2, 'aave.png', 'Aave is an Open Source Protocol to create Non-Custodial Liquidity Markets to earn interest on supplying and borrowing assets with a variable or stable ...', 9, '0000-00-00 00:00:00', 9),
(24, 'IOTA MIOTA', 15, '', '', '', 3, 'miota.png', 'IOTA has fundamentally reengineered distributed ledger technology, enabling secure exchange of both value and data, without any fees.', 9, '2023-06-20 12:16:00', 17),
(25, 'TrueUSD TUSD', 1, '', '', '', 4, 'tusd.png', 'TUSD is the first digital asset with live on-chain attestations by independent third-party institutions and is backed 1:1 with the U.S. dollar (USD). So far, it has been listed on more than 100 trading platforms such as Binance and Huobi and is live on 12 mainstream public chains, including Ethereum, TRON, Avalanche, BSC, Fantom, and Polygon.', 9, '2023-06-30 00:00:00', 5),
(26, 'testbra taqqerr', 15620, '', '', 'red', 0, 'default-image.png', 'wefffffffffffds fweeeeee wfe', 11, '2023-07-06 06:00:00', 34),
(27, 'kalajadid', 19999, NULL, NULL, NULL, 1, 'nophoto.png', 'cdsomvwm new kala', 11, NULL, 0),
(28, 'testp1', 1000, NULL, NULL, NULL, 4, 'nophoto.png', 'test tessssssst', 16, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `uID` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_persian_ci NOT NULL,
  `password` varchar(24) COLLATE utf8mb4_persian_ci NOT NULL,
  `name` varchar(24) COLLATE utf8mb4_persian_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_persian_ci NOT NULL,
  `mobile` varchar(30) COLLATE utf8mb4_persian_ci NOT NULL,
  `jensiat` varchar(1) COLLATE utf8mb4_persian_ci NOT NULL DEFAULT '0',
  `address` varchar(300) COLLATE utf8mb4_persian_ci NOT NULL,
  `profileimage` varchar(70) COLLATE utf8mb4_persian_ci NOT NULL DEFAULT 'nophoto.png'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_persian_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uID`, `username`, `password`, `name`, `email`, `mobile`, `jensiat`, `address`, `profileimage`) VALUES
(8, 'testuser', 'testpassword', 'Hassan Nasrollahi', 'h.nasrollahi@outlook.de', '09368413419', '0', 'Iran', '../userimages/testuserptrofile.png'),
(9, 'user1', 'user1', 'First User', 'user1@gmail.com', '09900001234', '1', 'Germany', '../userimages/user1.png'),
(10, 'userali', 'ali', 'ali', 'ali@gmail.com', '', '0', '', '../userimages/nophoto.png'),
(11, 'profile', 'profile', 'profile', 'profile@gmail.com', '12345', '0', 'aaa', '../userimages/nophoto.png'),
(12, 'farzinj95', 'farzin', 'farzin', 'farzin@gmail.com', '09111115555', '0', 'tehran', 'nophoto.png'),
(13, 'newuser', 'user', 'new user', 'newuser@gmail.com', '0925646644', '0', 'africa', 'nophoto.png'),
(14, 'user2', 'user2', 'usertwo', 'user2@gmail.com', '09112365648', '1', 'tehran', 'nophoto.png'),
(15, 'user3', 'user3', 'usertthree', 'user2@gmail.com', '09112365648', '1', 'tehran', 'nophoto.png'),
(16, 'user4', 'user4', 'userfour', 'user4@gmail.com', '09112659869', '0', 'iran', 'nophoto.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`catID`),
  ADD UNIQUE KEY `catName` (`catName`);

--
-- Indexes for table `kala`
--
ALTER TABLE `kala`
  ADD PRIMARY KEY (`kID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `catID` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `kala`
--
ALTER TABLE `kala`
  MODIFY `kID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `uID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
