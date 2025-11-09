USE [MSS301Summer25DBBrand]


INSERT [dbo].[blind_boxes_brand] ( [brand_name], [country_of_origin]) VALUES ( N'POP MART', N'China')

INSERT [dbo].[blind_boxes_brand] ( [brand_name], [country_of_origin]) VALUES ( N'Funko', N'USA')

INSERT [dbo].[blind_boxes_brand] ( [brand_name], [country_of_origin]) VALUES ( N'Kidrobot', N'USA')



INSERT [dbo].[blind_boxes] ([brand_id], [price], [release_date], [stock], [name], [rarity]) VALUES ( 1, 29.99, CAST(N'2024-01-15' AS Date), 150, N'Mystic Creatures Series 1', N'Rare')

INSERT [dbo].[blind_boxes] ([brand_id], [price], [release_date], [stock], [name], [rarity]) VALUES ( 2, 49.99, CAST(N'2023-11-20' AS Date), 75, N'Cyberpunk Warriors', N'Ultra Rare')

INSERT [dbo].[blind_boxes] ([brand_id], [price], [release_date], [stock], [name], [rarity]) VALUES ( 1, 19.99, CAST(N'2024-02-10' AS Date), 200, N'Fantasy Legends', N'Common')

INSERT [dbo].[blind_boxes] ([brand_id], [price], [release_date], [stock], [name], [rarity]) VALUES ( 3, 59.99, CAST(N'2023-12-05' AS Date), 50, N'Space Explorers', N'Epic')

INSERT [dbo].[blind_boxes] ([brand_id], [price], [release_date], [stock], [name], [rarity]) VALUES ( 1, 99.99, CAST(N'2024-03-01' AS Date), 25, N'Neon Anime Stars', N'Legendary')

INSERT [dbo].[blind_boxes] ([brand_id], [price], [release_date], [stock], [name], [rarity]) VALUES ( 2, 24.99, CAST(N'2024-01-30' AS Date), 180, N'Retro Arcade Heroes', N'Common')

INSERT [dbo].[blind_boxes] ([brand_id], [price], [release_date], [stock], [name], [rarity]) VALUES ( 3, 54.99, CAST(N'2023-10-10' AS Date), 60, N'Mythical Beasts Collection', N'Ultra Rare')


