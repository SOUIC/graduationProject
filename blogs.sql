-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2022-12-06 11:36:50
-- 服务器版本： 5.6.50-log
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blogs`
--

-- --------------------------------------------------------

--
-- 表的结构 `articles`
--

CREATE TABLE IF NOT EXISTS `articles` (
  `articles_id` int(11) NOT NULL COMMENT '文章id',
  `title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  `classification` varchar(255) DEFAULT NULL COMMENT '分类',
  `last_modified_date` varchar(255) DEFAULT NULL COMMENT '最后修改日期',
  `abstracts` varchar(255) DEFAULT NULL COMMENT '摘要',
  `recycle_bin_state` int(11) DEFAULT '0' COMMENT '0不再回收站\n1在回收站',
  `url` varchar(255) DEFAULT NULL COMMENT '文章文件路径'
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- --------------------------------------------------------

--
-- 表的结构 `classify`
--

CREATE TABLE IF NOT EXISTS `classify` (
  `classify_id` int(11) NOT NULL COMMENT '分类id',
  `classify` varchar(255) NOT NULL COMMENT '分类'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- --------------------------------------------------------

--
-- 表的结构 `dynamic`
--

CREATE TABLE IF NOT EXISTS `dynamic` (
  `dynamic_id` int(11) NOT NULL COMMENT '动态id',
  `content` varchar(255) DEFAULT NULL COMMENT '动态内容',
  `time` varchar(255) DEFAULT NULL COMMENT '动态时间'
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='动态表';


-- --------------------------------------------------------

--
-- 表的结构 `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `time` varchar(255) DEFAULT NULL COMMENT '操作时间',
  `operating_content` varchar(255) DEFAULT NULL COMMENT '操作详细'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日志表';


--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_name` varchar(255) DEFAULT 'blogs' COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `head_url` varchar(255) DEFAULT 'http://47.109.23.86:8060/images/default_image.png' COMMENT '头像url',
  `construction_time` varchar(255) NOT NULL COMMENT '建站时间',
  `announcement` varchar(255) DEFAULT NULL COMMENT '公告',
  `visitor_Volume` int(11) DEFAULT NULL COMMENT '网站访问量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`articles_id`),
  ADD UNIQUE KEY `id` (`articles_id`);

--
-- Indexes for table `classify`
--
ALTER TABLE `classify`
  ADD PRIMARY KEY (`classify_id`),
  ADD UNIQUE KEY `classify_id` (`classify_id`);

--
-- Indexes for table `dynamic`
--
ALTER TABLE `dynamic`
  ADD PRIMARY KEY (`dynamic_id`),
  ADD UNIQUE KEY `dynamic_id` (`dynamic_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articles`
--
ALTER TABLE `articles`
  MODIFY `articles_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',AUTO_INCREMENT=95;
--
-- AUTO_INCREMENT for table `classify`
--
ALTER TABLE `classify`
  MODIFY `classify_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `dynamic`
--
ALTER TABLE `dynamic`
  MODIFY `dynamic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态id',AUTO_INCREMENT=25;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
