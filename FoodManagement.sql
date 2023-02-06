USE [master]
GO
/****** Object:  Database [FoodManagement]    Script Date: 03/11/2022 18:59:52 ******/
CREATE DATABASE [FoodManagement] 
GO

USE [FoodManagement]
GO

CREATE TABLE [dbo].[tblUsers](
	[userID] [varchar](50) NOT NULL,
	[fullName] [nvarchar](500) NOT NULL,
	[roleID] [nvarchar](5) NOT NULL,
	[password] [varchar](50) NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblUsers] ([userID], [fullName], [roleID], [password]) VALUES (N'ADMIN', N'Toi la Administrator', N'ADMIN', N'ADMIN')
INSERT [dbo].[tblUsers] ([userID], [fullName], [roleID], [password]) VALUES (N'FPTU', N'fpt', N'USER', N'FPTU')
/****** Object:  Table [dbo].[tblFoods]    Script Date: 03/11/2022 18:59:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblFoods](
	[id] [char](5) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[description] [nvarchar](500) NOT NULL,
	[price] [float] NOT NULL,
	[cookingTime] [int] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblFoods] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblFoods] ([id], [name], [description], [price], [cookingTime], [status]) VALUES (N'F-001', N'Hot soup a', N'Soup với thịt gà tre', 20, 1, 1)
INSERT [dbo].[tblFoods] ([id], [name], [description], [price], [cookingTime], [status]) VALUES (N'F-002', N'Green soup a', N'Chè đậu đỏ may mắn', 30, 1, 0)
INSERT [dbo].[tblFoods] ([id], [name], [description], [price], [cookingTime], [status]) VALUES (N'F-004', N'Steak a', N'Thịt bò Củ Chi nướng tái', 80, 2, 1)
INSERT [dbo].[tblFoods] ([id], [name], [description], [price], [cookingTime], [status]) VALUES (N'F-005', N'Chiken fire a', N'Gà cháy cạnh', 70, 3, 1)
INSERT [dbo].[tblFoods] ([id], [name], [description], [price], [cookingTime], [status]) VALUES (N'F003 ', N'Rice a', N'Gạo nàng thơm chợ đào', 10, 4, 0)
